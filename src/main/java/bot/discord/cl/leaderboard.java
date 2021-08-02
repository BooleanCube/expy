package bot.discord.cl;

import bot.Constants;
import bot.discord.Tools;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class leaderboard extends ListenerAdapter {

    public static HashMap<Long, LBER> embedMap = new HashMap<>();
    ArrayList<Long> lbMsgs = new ArrayList<>();

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        String msg = event.getMessage().getContentRaw();
        if(lbMsgs.size()>29) {
            embedMap.remove(lbMsgs.get(0));
            lbMsgs.remove(0);
        }
        if(msg.equalsIgnoreCase("e!leaderboard") || msg.equalsIgnoreCase("e!lb")) {
            if(!event.getChannel().getId().equals(Constants.channelID)) {
                event.getMessage().delete().queue();
                MessageEmbed me = new EmbedBuilder()
                        .setDescription("Please only use my commands in <#670159727547777054>")
                        .setColor(Color.RED)
                        .build();
                event.getChannel().sendMessage(me).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
                return;
            }
            ArrayList<String> leaderBoard = null;
            try { leaderBoard = Tools.getLeaderBoard(event.getGuild(), event.getMember()); } catch (IOException e) { e.printStackTrace(); }
            if(leaderBoard.size()==0) leaderBoard.add("Nobody is on the Leaderboard! Use trivia commands and send messages to gain XP!");
            ArrayList<String> lb = leaderBoard;
            try {
                MessageEmbed me = new EmbedBuilder()
                        .setTitle(event.getGuild().getName() + "'s Leaderboard")
                        .setThumbnail("https://media.discordapp.net/attachments/830844105230909490/831279813536776202/sports-medal_1f3c5.png?width=108&height=108")
                        .setAuthor("Your Rank: " + Tools.getRank(event.getMember(), event.getGuild()), event.getAuthor().getEffectiveAvatarUrl(), event.getAuthor().getEffectiveAvatarUrl())
                        .setDescription("`[Rank #]. [User Tag] (XP)`\n" + leaderBoard.get(0))
                        .setFooter("Page 1/" + leaderBoard.size())
                        .build();
                event.getChannel().sendMessage(me).queue(m -> {
                    m.addReaction(LBER.fastBack).queue();
                    m.addReaction(LBER.leftArrow).queue();
                    m.addReaction(LBER.rightArrow).queue();
                    m.addReaction(LBER.fastForward).queue();
                    m.removeReaction(LBER.fastBack).queueAfter(5, TimeUnit.MINUTES);
                    m.removeReaction(LBER.fastForward).queueAfter(5, TimeUnit.MINUTES);
                    m.removeReaction(LBER.leftArrow).queueAfter(5, TimeUnit.MINUTES);
                    m.removeReaction(LBER.rightArrow).queueAfter(5, TimeUnit.MINUTES, message -> { embedMap.remove(m.getIdLong()); });
                    embedMap.put(m.getIdLong(), new LBER(m, lb, me, event.getMember()));
                    lbMsgs.add(m.getIdLong());
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

