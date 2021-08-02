package bot.discord.cl;

import bot.discord.API;
import bot.Constants;
import bot.discord.Question;
import bot.discord.Tools;
import bot.discord.UserStatistics;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.Button;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Member;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class trivia extends ListenerAdapter {

    public static HashMap<Long, Question> openQuestions = new HashMap<>();
    public static HashMap<Long, Long> timeoutMap = new HashMap<>();
    public static HashMap<Long, ScheduledFuture> timers = new HashMap<>();

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        String msg = event.getMessage().getContentRaw();
        if(msg.equalsIgnoreCase(Constants.prefix + "trivia")) {
            if(!event.getChannel().getId().equals(Constants.channelID)) {
                event.getMessage().delete().queue();
                event.getChannel().sendMessage(
                        new EmbedBuilder()
                                .setDescription("Please only use my commands in <#670159727547777054>")
                                .setColor(Color.RED)
                                .build()
                ).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
                return;
            }
            if(timeoutMap.containsKey(event.getMember().getIdLong())) {
                event.getMessage().delete().queue();
                long lastAttempt = System.currentTimeMillis() - timeoutMap.get(event.getMember().getIdLong());
                if(lastAttempt < 10*60*1000) {
                    event.getChannel().sendMessage(
                            new EmbedBuilder()
                                    .setDescription("This command is on cooldown!\nPlease try again in `" + Tools.secondsToTime((10*60)-(long)(lastAttempt/1000.0)) + "`")
                                    .setColor(Color.RED)
                                    .build()
                    ).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
                    return;
                } else timeoutMap.remove(event.getMember().getIdLong());
            }
            Question q = API.getQuestion();
            if(q==null) {
                event.getChannel().sendMessage(
                        new EmbedBuilder()
                                .setDescription("Whoops! I failed to retrieve a trivia question! <:pepeSus:830654727540178964>\nPlease try again later!")
                                .setColor(Color.RED)
                                .build()
                ).queue();
                return;
            }
            Collections.shuffle(q.answers);
            StringBuilder sb = new StringBuilder("");
            String[] emojis = {":one:", ":two:", ":three:", ":four:"};
            for(int i=0; i<q.answers.size(); i++) sb.append(emojis[i] + " " + q.answers.get(i).replaceAll("&uacute;", "u").replaceAll("&amp;", "&").replaceAll("&Aacute;", "A").replaceAll("&eacute;", "e").replaceAll("&quot;", "\"").replaceAll("&#039;", "'").replaceAll("&apos;", "'").replaceAll("&gt;", ">").replaceAll("&lt;", "<") + "\n");
            EmbedBuilder e = new EmbedBuilder()
                    .setAuthor(event.getMember().getEffectiveName(), event.getAuthor().getAvatarUrl(), event.getAuthor().getEffectiveAvatarUrl())
                    .setTitle("**" + q.question + "**")
                    .setDescription("XP Worth: `" + q.XP + " XP`")
                    .setFooter("You have 15 seconds to answer this question! The clock is ticking..")
                    .addField("", sb.toString(), true);
            openQuestions.put(event.getMember().getIdLong(), q);
            timeoutMap.put(event.getMember().getIdLong(), System.currentTimeMillis());
            event.getChannel().sendMessage(e.build()).setActionRow(
                    Button.secondary("1", Emoji.fromUnicode("1️⃣")),
                    Button.secondary("2", Emoji.fromUnicode("2️⃣")),
                    Button.secondary("3", Emoji.fromUnicode("3️⃣")),
                    Button.secondary("4", Emoji.fromUnicode("4️⃣"))
            ).queue();
            UserStatistics user = null;
            try { user = UserStatistics.retrieveUserStatistics(event.getMember()); } catch (IOException ioe) { ioe.printStackTrace(); }
            final UserStatistics userEmbed = user;
            int b = (int)(Math.random()*100)%5; b+=1;
            final int p = b;
            timers.put(event.getMember().getIdLong(), event.getChannel().sendMessage(new EmbedBuilder()
                    .setTitle("Time Up!")
                    .setColor(Color.BLACK)
                    .setDescription("The correct answer was `" + q.correctAnswer + "`\nYou lost `" + p + " XP` <:monkaHmm:830876985193791498>")
                    .build()
            ).queueAfter(15, TimeUnit.SECONDS, message -> {
                try { Tools.addXP(event.getMember(),p*-1); } catch (IOException ioe) { ioe.printStackTrace(); }
                trivia.openQuestions.remove(event.getMember().getIdLong());
                ++userEmbed.attemptedTrivia;
                ++userEmbed.wrongTrivia;
                userEmbed.currentStreak = 0;
                userEmbed.xpGainedFromTrivia -= p;
                try { UserStatistics.updateDatabase(event.getMember(), userEmbed); } catch (IOException ioe) { ioe.printStackTrace(); }
            }));
        }
    }
}
