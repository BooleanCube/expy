package bot.discord.cl;

import bot.Constants;
import bot.discord.Tools;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class syncxp extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        String msg = event.getMessage().getContentRaw();
        if(msg.equalsIgnoreCase("e!sync")) {
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
            Role bronze = event.getGuild().getRolesByName("Bronze", true).get(0);
            Role silver = event.getGuild().getRolesByName("Silver", true).get(0);
            Role gold = event.getGuild().getRolesByName("Gold", true).get(0);
            Role platinum = event.getGuild().getRolesByName("Platinum", true).get(0);
            Role diamond = event.getGuild().getRolesByName("Diamond", true).get(0);
            Role master = event.getGuild().getRolesByName("Master", true).get(0);
            Role grandmaster = event.getGuild().getRolesByName("Grand Master", true).get(0);
            try {
                if(event.getMember().getRoles().contains(grandmaster)) {
                    if(Tools.getLevelFromXP(Tools.getXP(event.getMember())) < 200) {
                        Tools.addXP(event.getMember(), Tools.getXPFromLevel(200) - Tools.getXP(event.getMember()));
                        event.getMessage().delete().queue();
                        event.getChannel().sendMessage(new EmbedBuilder().setDescription("Successfully synced your XP according to your highest role!").build()).queue(
                                m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
                        return;
                    }
                    event.getMessage().delete().queue();
                    event.getChannel().sendMessage(new EmbedBuilder().setDescription("Your XP is already in sync with to your roles!").build()).queue(
                            m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
                    return;
                }
                else if(event.getMember().getRoles().contains(master)) {
                    if(Tools.getLevelFromXP(Tools.getXP(event.getMember())) < 120) {
                        Tools.addXP(event.getMember(), Tools.getXPFromLevel(120) - Tools.getXP(event.getMember()));
                        event.getMessage().delete().queue();
                        event.getChannel().sendMessage(new EmbedBuilder().setDescription("Successfully synced your XP according to your highest role!").build()).queue(
                                m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
                        return;
                    }
                    event.getMessage().delete().queue();
                    event.getChannel().sendMessage(new EmbedBuilder().setDescription("Your XP is already in sync with to your roles!").build()).queue(
                            m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
                    return;
                }
                else if(event.getMember().getRoles().contains(diamond)) {
                    if(Tools.getLevelFromXP(Tools.getXP(event.getMember())) < 80) {
                        Tools.addXP(event.getMember(), Tools.getXPFromLevel(80) - Tools.getXP(event.getMember()));
                        event.getMessage().delete().queue();
                        event.getChannel().sendMessage(new EmbedBuilder().setDescription("Successfully synced your XP according to your highest role!").build()).queue(
                                m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
                        return;
                    }
                    event.getMessage().delete().queue();
                    event.getChannel().sendMessage(new EmbedBuilder().setDescription("Your XP is already in sync with to your roles!").build()).queue(
                            m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
                    return;
                }
                if(event.getMember().getRoles().contains(platinum)) {
                    if(Tools.getLevelFromXP(Tools.getXP(event.getMember())) < 40) {
                        Tools.addXP(event.getMember(), Tools.getXPFromLevel(40) - Tools.getXP(event.getMember()));
                        event.getMessage().delete().queue();
                        event.getChannel().sendMessage(new EmbedBuilder().setDescription("Successfully synced your XP according to your highest role!").build()).queue(
                                m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
                        return;
                    }
                    event.getMessage().delete().queue();
                    event.getChannel().sendMessage(new EmbedBuilder().setDescription("Your XP is already in sync with to your roles!").build()).queue(
                            m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
                    return;
                }
                else if(event.getMember().getRoles().contains(gold)) {
                    if(Tools.getLevelFromXP(Tools.getXP(event.getMember())) < 20) {
                        Tools.addXP(event.getMember(), Tools.getXPFromLevel(20) - Tools.getXP(event.getMember()));
                        event.getMessage().delete().queue();
                        event.getChannel().sendMessage(new EmbedBuilder().setDescription("Successfully synced your XP according to your highest role!").build()).queue(
                                m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
                        return;
                    }
                    event.getMessage().delete().queue();
                    event.getChannel().sendMessage(new EmbedBuilder().setDescription("Your XP is already in sync with to your roles!").build()).queue(
                            m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
                    return;
                }
                else if(event.getMember().getRoles().contains(silver)) {
                    if(Tools.getLevelFromXP(Tools.getXP(event.getMember())) < 10) {
                        Tools.addXP(event.getMember(), Tools.getXPFromLevel(10) - Tools.getXP(event.getMember()));
                        event.getMessage().delete().queue();
                        event.getChannel().sendMessage(new EmbedBuilder().setDescription("Successfully synced your XP according to your highest role!").build()).queue(
                                m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
                        return;
                    }
                    event.getMessage().delete().queue();
                    event.getChannel().sendMessage(new EmbedBuilder().setDescription("Your XP is already in sync with to your roles!").build()).queue(
                            m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
                    return;
                }
                else if(event.getMember().getRoles().contains(bronze)) {
                    if(Tools.getLevelFromXP(Tools.getXP(event.getMember())) < 5) {
                        Tools.addXP(event.getMember(), Tools.getXPFromLevel(5) - Tools.getXP(event.getMember()));
                        event.getMessage().delete().queue();
                        event.getChannel().sendMessage(new EmbedBuilder().setDescription("Successfully synced your XP according to your highest role!").build()).queue(
                                m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
                        return;
                    }
                    event.getMessage().delete().queue();
                    event.getChannel().sendMessage(new EmbedBuilder().setDescription("Your XP is already in sync with to your roles!").build()).queue(
                            m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
                    return;
                }
                event.getChannel().sendMessage(new EmbedBuilder().setDescription("You don't have any roles to sync with!").build()).queue();
            } catch (IOException e) {
                event.getChannel().sendMessage(new EmbedBuilder().setDescription("Something went wrong! Please try again later!").build()).queue();
            }
        }
    }
}
