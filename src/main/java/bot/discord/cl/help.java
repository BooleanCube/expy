package bot.discord.cl;

import bot.Constants;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class help extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        String msg = event.getMessage().getContentRaw();
        if(msg.equalsIgnoreCase("e!help")) {
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
            event.getChannel().sendMessage(
                    new EmbedBuilder()
                            .setTitle("Commands")
                            .setThumbnail("https://media.discordapp.net/attachments/830844105230909490/864283192366071818/expymaybe.png?width=600&height=600")
                            .addField("XP", "`xp` | `leaderboard` | `sync`", false)
                            .addField("Trivia", "`trivia` | `stats` | `ranks`", false)
                            .addField("Miscellaneous", "`help` | `ping`", false)
                            .setFooter("For more info about a command type e!help [command]")
                            .build()
            ).queue();
        } else if(msg.equalsIgnoreCase("e!help help")) {
            event.getChannel().sendMessage(new EmbedBuilder()
                    .setTitle("Help Command")
                    .setDescription("Sends an embed with a list of commands available.")
                    .addField("Usage", "`e!help`", false)
                    .setFooter("If you notice any bugs with me, contact BooleanCube#4690")
                    .build()
            ).queue();
        } else if(msg.equalsIgnoreCase("e!help ping")) {
            event.getChannel().sendMessage(new EmbedBuilder()
                    .setTitle("Ping Command")
                    .setDescription("Shows the statistics of my latency and my gateway ping.")
                    .addField("Usage", "`e!ping`", false)
                    .setFooter("If you notice any bugs with me, contact BooleanCube#4690")
                    .build()
            ).queue();
        } else if(msg.equalsIgnoreCase("e!help trivia")) {
            event.getChannel().sendMessage(new EmbedBuilder()
                    .setTitle("Trivia Command")
                    .setDescription("Opens a trivia question for the user who used the command!\nThere is a 30 minute timeout between each trivia request for each member to prevent spamming. Every user will be given exactly 15 seconds to answer each trivia question. If you answer correctly you gain the number of XP the question was worth (55-70 XP), however, if you answer incorrectly then you lose 5-10 XP and if you don't answer you will lose 1-5 XP.")
                    .addField("Usage", "`e!trivia`", false)
                    .setFooter("If you notice any bugs with me, contact BooleanCube#4690")
                    .build()
            ).queue();
        } else if(msg.equalsIgnoreCase("e!help xp")) {
            event.getChannel().sendMessage(new EmbedBuilder()
                    .setTitle("XP Command")
                    .setDescription("Shows the amount of XP a user has.")
                    .addField("Usage", "`e!xp [user(optional)]`", false)
                    .setFooter("If you notice any bugs with me, contact BooleanCube#4690")
                    .build()
            ).queue();
        } else if(msg.equalsIgnoreCase("e!help stats")) {
            event.getChannel().sendMessage(new EmbedBuilder()
                    .setTitle("Statistics Command")
                    .setDescription("Shows the user's trivia statistics.")
                    .addField("Usage", "`e!stats [user(optional)]`", false)
                    .setFooter("If you notice any bugs with me, contact BooleanCube#4690")
                    .build()
            ).queue();
        } else if(msg.equalsIgnoreCase("e!help leaderboard") || msg.equalsIgnoreCase("e!help lb")) {
            event.getChannel().sendMessage(new EmbedBuilder()
                    .setTitle("Leaderboard Command")
                    .setDescription("Shows a leaderboard of the top users in the server.")
                    .addField("Usage", "`e!leaderboard`", false)
                    .setFooter("If you notice any bugs with me, contact BooleanCube#4690")
                    .build()
            ).queue();
        } else if(msg.equalsIgnoreCase("e!help sync")) {
            event.getChannel().sendMessage(new EmbedBuilder()
                    .setTitle("Sync XP Command")
                    .setDescription("Syncs your XP according to your roles.")
                    .addField("Usage", "`e!sync`", false)
                    .setFooter("If you notice any bugs with me, contact BooleanCube#4690")
                    .build()
            ).queue();
        } else if(msg.equalsIgnoreCase("e!help ranks")) {
            event.getChannel().sendMessage(new EmbedBuilder()
                    .setTitle("Ranks Command")
                    .setDescription("Shows the top 10 ranks of the server.")
                    .addField("Usage", "`e!ranks [ratio/streak]`", false)
                    .setFooter("If you notice any bugs with me, contact BooleanCube#4690")
                    .build()
            ).queue();
        }
    }
}