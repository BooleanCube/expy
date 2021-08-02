package bot.discord.cl;

import bot.discord.Tools;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;

public class xplistener extends ListenerAdapter {

    HashMap<Long, Long> lastMsgTime = new HashMap<>();

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if(event.getAuthor().isBot()) return;
        try {
            if(lastMsgTime.containsKey(event.getAuthor().getIdLong()) && System.currentTimeMillis()-lastMsgTime.get(event.getAuthor().getIdLong()) < 60000) return;
            else if(lastMsgTime.containsKey(event.getAuthor().getIdLong())) lastMsgTime.remove(event.getAuthor().getIdLong());
            lastMsgTime.put(event.getAuthor().getIdLong(), System.currentTimeMillis());
            int xpAdd = Tools.getRandXP();
            try { Tools.addXP(event.getMember(), xpAdd); } catch (IOException e) { e.printStackTrace(); }
            Role bronze = event.getGuild().getRolesByName("Bronze", true).get(0);
            Role silver = event.getGuild().getRolesByName("Silver", true).get(0);
            Role gold = event.getGuild().getRolesByName("Gold", true).get(0);
            Role platinum = event.getGuild().getRolesByName("Platinum", true).get(0);
            if(Tools.getLevelFromXP(Tools.getXP(event.getMember())) >= 40) {
                if(event.getMember().getRoles().contains(platinum)) return;
                if(platinum!=null) event.getGuild().addRoleToMember(event.getMember(), event.getGuild().getRolesByName("platinum", true).get(0)).queue();
                event.getAuthor().openPrivateChannel().queue(channel -> {channel.sendMessage(new EmbedBuilder().setDescription("You gained the `Platinum` rank in Kristofer Yee!").build()).queue();});
                //if(event.getGuild().getRolesByName("Gold", true).size() >= 1) event.getGuild().removeRoleFromMember(event.getMember(), event.getGuild().getRolesByName("gold", true).get(0)).queue();
                return;
            }
            else if(Tools.getLevelFromXP(Tools.getXP(event.getMember())) >= 20) {
                if(event.getMember().getRoles().contains(gold)) return;
                if(gold!=null) event.getGuild().addRoleToMember(event.getMember(), event.getGuild().getRolesByName("gold", true).get(0)).queue();
                event.getAuthor().openPrivateChannel().queue(channel -> {channel.sendMessage(new EmbedBuilder().setDescription("You gained the `Gold` rank in Kristofer Yee!").build()).queue();});
                //if(event.getGuild().getRolesByName("Silver", true).size() >= 1) event.getGuild().removeRoleFromMember(event.getMember(), event.getGuild().getRolesByName("silver", true).get(0)).queue();
                return;
            }
            else if(Tools.getLevelFromXP(Tools.getXP(event.getMember())) >= 10) {
                if(event.getMember().getRoles().contains(silver)) return;
                if(silver!=null) event.getGuild().addRoleToMember(event.getMember(), event.getGuild().getRolesByName("silver", true).get(0)).queue();
                event.getAuthor().openPrivateChannel().queue(channel -> {channel.sendMessage(new EmbedBuilder().setDescription("You gained the `Silver` rank in Kristofer Yee!").build()).queue();});
                //if(event.getGuild().getRolesByName("Bronze", true).size() >= 1) event.getGuild().removeRoleFromMember(event.getMember(), event.getGuild().getRolesByName("bronze", true).get(0)).queue();
                return;
            }
            else if(Tools.getLevelFromXP(Tools.getXP(event.getMember())) >= 5) {
                if(event.getMember().getRoles().contains(bronze)) return;
                if(bronze!=null) event.getGuild().addRoleToMember(event.getMember(), event.getGuild().getRolesByName("bronze", true).get(0)).queue();
                event.getAuthor().openPrivateChannel().queue(channel -> {channel.sendMessage(new EmbedBuilder().setDescription("You gained the `Bronze` rank in Kristofer Yee!").build()).queue();});
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}