package bot.discord.cl;

import bot.Constants;
import bot.discord.Tools;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class XP extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        String msg = event.getMessage().getContentRaw();
        if(event.getAuthor().isBot()) return;
        if(msg.equalsIgnoreCase("e!xp")) {
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
            try {
                int xp = Tools.getXP(event.getMember());
                int level = Tools.getLevelFromXP(xp);
                int rank = Tools.getRank(event.getMember(), event.getGuild());
                event.getChannel().sendMessage(
                        new EmbedBuilder()
                                .setAuthor(event.getAuthor().getName(), event.getAuthor().getEffectiveAvatarUrl(), event.getAuthor().getEffectiveAvatarUrl())
                                .addField("Level", String.valueOf(Tools.getLevelFromXP(Tools.getXP(event.getMember()))), true)
                                .addField("XP",  Tools.getXP(event.getMember()) + "/" + (Tools.getXPFromLevel(Tools.getLevelFromXP(Tools.getXP(event.getMember()))+1)), true)
                                .addField("Rank", rank==-1 ? "Not ranked" : String.valueOf(rank), true)
                                .build()
                ).queue();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(msg.split(" ").length >= 2 && msg.split(" ")[0].equalsIgnoreCase("e!xp")) {
            if(!event.getChannel().getId().equals(Constants.channelID)) {
                event.getMessage().delete().queue();
                event.getChannel().sendMessage(
                        new EmbedBuilder()
                                .setDescription("Please only use my commands in <#670159727547777054>")
                                .setColor(Color.RED)
                                .build()
                ).queue(ms -> ms.delete().queueAfter(5, TimeUnit.SECONDS));
                return;
            }
            Member m = Tools.getEffectiveMember(event.getGuild(), msg.split(" " ,2)[1]);
            if(m==null) {
                event.getChannel().sendMessage(
                        new EmbedBuilder()
                                .setDescription("I could not find the user specified in this server! Please try that again with either a name, an ID, or a mention of that user!")
                                .setColor(Color.RED)
                                .build()
                ).queue();
                return;
            }
            try {
                int rank = Tools.getRank(m, event.getGuild());
                event.getChannel().sendMessage(
                        new EmbedBuilder()
                                .setAuthor(m.getUser().getName(), m.getUser().getEffectiveAvatarUrl(), m.getUser().getEffectiveAvatarUrl())
                                .addField("Level", String.valueOf(Tools.getLevelFromXP(Tools.getXP(m))), true)
                                .addField("XP", Tools.getXP(m) + "/" + Tools.getXPFromLevel(Tools.getLevelFromXP(Tools.getXP(m))+1), true)
                                .addField("Rank", rank==-1 ? "Not ranked" : String.valueOf(rank), true)
                                .build()
                ).queue();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
