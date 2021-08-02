package bot.discord.cl;

import bot.Constants;
import bot.discord.Question;
import bot.discord.Tools;
import bot.discord.UserStatistics;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.IOException;

public class trivialistener extends ListenerAdapter {

    @Override
    public void onButtonClick(@NotNull ButtonClickEvent event) {
        if(!event.getChannel().getId().equals(Constants.channelID)) return;
        if(trivia.openQuestions.containsKey(event.getMember().getIdLong())) {
            String ans = event.getButton().getId();
            if(ans.equals("1") || ans.equals("2") || ans.equals("3") || ans.equals("4")) {
                UserStatistics user = null;
                try { user = UserStatistics.retrieveUserStatistics(event.getMember()); } catch (IOException e) { e.printStackTrace(); }
                ++user.attemptedTrivia;
                Question q = trivia.openQuestions.get(event.getMember().getIdLong());
                int userAnswerInt = Integer.parseInt(ans);
                String userAnswer = q.answers.get(userAnswerInt-1);
                if(userAnswer.equalsIgnoreCase(q.correctAnswer)) {
                    q.correctAnswer = q.correctAnswer.replaceAll("&amp;", "&").replaceAll("&uacute;", "u").replaceAll("&quot;", "\"").replaceAll("&Aacute;", "A").replaceAll("&#039;", "'").replaceAll("&apos;", "'").replaceAll("&gt;", ">").replaceAll("&lt;", "<").replaceAll("&eacute;", "e");
                    try { Tools.addXP(event.getMember(), q.XP); } catch (IOException e) { e.printStackTrace(); }
                    try {
                        event.replyEmbeds(
                                new EmbedBuilder()
                                        .setTitle("Correct!")
                                        .setColor(Color.GREEN)
                                        .setDescription("The correct answer was `" + q.correctAnswer.trim() + "`\nYou gained `" + q.XP + " XP`")
                                        .setFooter("Congrats!", "https://media.discordapp.net/attachments/830844105230909490/869254651529281607/HYPERS.png?width=143&height=143")
                                        .build()
                        ).queue();
                        trivia.timers.get(event.getMember().getIdLong()).cancel(true);
                        trivia.timers.remove(event.getMember().getIdLong());
                        user.correctTrivia++;
                        user.increaseStreak();
                        user.xpGainedFromTrivia += q.XP;
                    } catch (Exception e) {}
                }
                else {
                    q.correctAnswer = q.correctAnswer.replaceAll("&amp;", "&").replaceAll("&uacute;", "u").replaceAll("&quot;", "\"").replaceAll("&Aacute;", "A").replaceAll("&#039;", "'").replaceAll("&apos;", "'").replaceAll("&gt;", ">").replaceAll("&lt;", "<").replaceAll("&eacute;", "e");
                    int p = (int)(Math.random()*100)%6; p+=5;
                    try { Tools.addXP(event.getMember(),p*-1); } catch (IOException e) { e.printStackTrace(); }
                    try {
                        event.replyEmbeds(
                                new EmbedBuilder()
                                        .setTitle("Wrong!")
                                        .setColor(Color.RED)
                                        .setDescription("The correct answer was `" + q.correctAnswer.trim() + "`\nYou lost `" + p + " XP`")
                                        .setFooter("Better luck next time!", "https://media.discordapp.net/attachments/830844105230909490/830844111170175046/824350120853504020.png?width=84&height=84")
                                        .build()
                        ).queue();
                        trivia.timers.get(event.getMember().getIdLong()).cancel(true);
                        trivia.timers.remove(event.getMember().getIdLong());
                        user.wrongTrivia++;
                        user.currentStreak = 0;
                        user.xpGainedFromTrivia -= p;
                    } catch (Exception e) {}
                }
                trivia.openQuestions.remove(event.getMember().getIdLong());
                try { UserStatistics.updateDatabase(event.getMember(), user); } catch (IOException e) { e.printStackTrace(); }
            }
        }
    }

}