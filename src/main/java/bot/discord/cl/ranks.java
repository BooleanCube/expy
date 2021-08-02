package bot.discord.cl;

import bot.Constants;
import bot.discord.Tools;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ranks extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        String msg = event.getMessage().getContentRaw();
        if(msg.split(" ")[0].equalsIgnoreCase("e!ranks") && msg.split(" ").length==2) {
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
            String inputType = msg.split(" ")[1];
            if(inputType.equalsIgnoreCase("cwr") || inputType.equalsIgnoreCase("ratio")) {
                EmbedBuilder s = null;
                try {
                    s = new EmbedBuilder()
                            .setTitle("Top 10 Ranks")
                            .setAuthor("CWR")
                            .setDescription(Tools.getRatioRanking(event.getGuild(), event.getMember()))
                            .setFooter("CWR is the Correct/Wrong Ratio. Get more right than wrong to move up the rankings!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                event.getChannel().sendMessage(s.build()).queue();
            } else if(inputType.equalsIgnoreCase("streak")) {
                EmbedBuilder s = null;
                try {
                    s = new EmbedBuilder()
                            .setTitle("Top 10 Ranks")
                            .setAuthor("Streak")
                            .setDescription(Tools.getStreakRanking(event.getGuild(), event.getMember()))
                            .setFooter("A streak is the amount of question you answer correctly continuously. Don't get questions wrong in the trivia!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                event.getChannel().sendMessage(s.build()).queue();
            }
        }
    }
}
