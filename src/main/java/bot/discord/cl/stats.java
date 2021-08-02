package bot.discord.cl;

import bot.Constants;
import bot.discord.Tools;
import bot.discord.UserStatistics;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class stats extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        String msg = event.getMessage().getContentRaw();
        if(msg.split(" ").length == 1 && msg.equalsIgnoreCase(Constants.prefix + "stats")) {
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
            UserStatistics user = null;
            try { user = UserStatistics.retrieveUserStatistics(event.getMember()); } catch (IOException e) { e.printStackTrace(); }
            event.getChannel().sendMessage(new EmbedBuilder()
                    .setAuthor(event.getMember().getEffectiveName(), event.getAuthor().getEffectiveAvatarUrl(), event.getAuthor().getEffectiveAvatarUrl())
                    .setTitle("User Trivia Statistics")
                    .addField("Questions:", "Attempted: " + user.attemptedTrivia + "\nCorrect: " + user.correctTrivia + "\nWrong: " + user.wrongTrivia, false)
                    .addField("CW Ratio", "Correct to Wrong Ratio = " + user.ratioCalculation(user.correctTrivia, user.wrongTrivia), false)
                    .addField("Streaks", "Current Streak: " + user.currentStreak + "\nLongest Streak: " + user.longestStreak, false)
                    .build()
            ).queue();
        } else if(msg.split(" ").length == 2 && msg.split(" ")[0].equalsIgnoreCase(Constants.prefix + "stats")) {
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
            Member m = Tools.getEffectiveMember(event.getGuild(), msg.split(" ")[1]);
            if(m==null) {
                event.getChannel().sendMessage(
                    new EmbedBuilder()
                            .setDescription("I could not find the user specified in this server! Please try that again with either a name, an ID, or a mention of that user!")
                            .setColor(Color.RED)
                            .build()
                ).queue();
                return;
            }
            UserStatistics user = null;
            try { user = UserStatistics.retrieveUserStatistics(m); } catch (IOException e) { e.printStackTrace(); }
            event.getChannel().sendMessage(new EmbedBuilder()
                    .setAuthor(m.getEffectiveName(), m.getUser().getEffectiveAvatarUrl(), m.getUser().getEffectiveAvatarUrl())
                    .setTitle("User Trivia Statistics")
                    .addField("Questions:", "Attempted: " + user.attemptedTrivia + "\nCorrect: " + user.correctTrivia + "\nWrong: " + user.wrongTrivia, false)
                    .addField("CW Ratio", "Correct to Wrong Ratio = " + user.ratioCalculation(user.correctTrivia, user.wrongTrivia), false)
                    .addField("Streaks", "Current Streak: " + user.currentStreak + "\nLongest Streak: " + user.longestStreak, false)
                    .build()
            ).queue();
        }
    }
}
