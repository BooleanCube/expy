package bot.discord.cl;

import bot.discord.Tools;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class leaderboardlistener extends ListenerAdapter {

    @Override
    public void onGuildMessageReactionAdd(@NotNull GuildMessageReactionAddEvent event) {
        if(leaderboard.embedMap.containsKey(event.getMessageIdLong())) {
            long id = event.getMessageIdLong();
            LBER l = leaderboard.embedMap.get(id);
            if(l.author.getIdLong() != event.getUserIdLong()) return;
            if(event.getReactionEmote().getEmoji().equals(LBER.leftArrow)) {
                event.getReaction().removeReaction(event.getUser()).queue();
                l.page--;
                if(l.page > l.leaderboard.size() || l.page < 1) { ++l.page; return; }
                try {
                    MessageEmbed me = new EmbedBuilder()
                            .setTitle(event.getGuild().getName() + "'s Leaderboard")
                            .setThumbnail("https://media.discordapp.net/attachments/830844105230909490/831279813536776202/sports-medal_1f3c5.png?width=108&height=108")
                            .setAuthor("Your Rank: " + Tools.getRank(event.getMember(), event.getGuild()), event.getUser().getEffectiveAvatarUrl(), event.getUser().getEffectiveAvatarUrl())
                            .setDescription("`[Rank #]. [User Tag] (XP)`\n" + l.leaderboard.get(l.page-1))
                            .setFooter("Page " + l.page + "/" + l.leaderboard.size())
                            .build();
                    l.m.editMessage(me).queue();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if(event.getReactionEmote().getEmoji().equals(LBER.rightArrow)) {
                event.getReaction().removeReaction(event.getUser()).queue();
                l.page++;
                if(l.page > l.leaderboard.size() || l.page < 0) { --l.page; return; }
                try {
                    MessageEmbed me = new EmbedBuilder()
                            .setTitle(event.getGuild().getName() + "'s Leaderboard")
                            .setThumbnail("https://media.discordapp.net/attachments/830844105230909490/831279813536776202/sports-medal_1f3c5.png?width=108&height=108")
                            .setAuthor("Your Rank: " + Tools.getRank(event.getMember(), event.getGuild()), event.getUser().getEffectiveAvatarUrl(), event.getUser().getEffectiveAvatarUrl())
                            .setDescription("`[Rank #]. [User Tag] (XP)`\n" + l.leaderboard.get(l.page-1))
                            .setFooter("Page " + l.page + "/" + l.leaderboard.size())
                            .build();
                    l.m.editMessage(me).queue();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if(event.getReactionEmote().getEmoji().equals(LBER.fastBack)) {
                event.getReaction().removeReaction(event.getUser()).queue();
                l.page-=10;
                if(l.page > l.leaderboard.size() || l.page < 0) { l.page+=10; return; }
                try {
                    MessageEmbed me = new EmbedBuilder()
                            .setTitle(event.getGuild().getName() + "'s Leaderboard")
                            .setThumbnail("https://media.discordapp.net/attachments/830844105230909490/831279813536776202/sports-medal_1f3c5.png?width=108&height=108")
                            .setAuthor("Your Rank: " + Tools.getRank(event.getMember(), event.getGuild()), event.getUser().getEffectiveAvatarUrl(), event.getUser().getEffectiveAvatarUrl())
                            .setDescription("`[Rank #]. [User Tag] (XP)`\n" + l.leaderboard.get(l.page-1))
                            .setFooter("Page " + l.page + "/" + l.leaderboard.size())
                            .build();
                    l.m.editMessage(me).queue();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if(event.getReactionEmote().getEmoji().equals(LBER.fastForward)) {
                event.getReaction().removeReaction(event.getUser()).queue();
                l.page+=10;
                if(l.page > l.leaderboard.size() || l.page < 0) { l.page-=10; return; }
                try {
                    MessageEmbed me = new EmbedBuilder()
                            .setTitle(event.getGuild().getName() + "'s Leaderboard")
                            .setThumbnail("https://media.discordapp.net/attachments/830844105230909490/831279813536776202/sports-medal_1f3c5.png?width=108&height=108")
                            .setAuthor("Your Rank: " + Tools.getRank(event.getMember(), event.getGuild()), event.getUser().getEffectiveAvatarUrl(), event.getUser().getEffectiveAvatarUrl())
                            .setDescription("`[Rank #]. [User Tag] (XP)`\n" + l.leaderboard.get(l.page-1))
                            .setFooter("Page " + l.page + "/" + l.leaderboard.size())
                            .build();
                    l.m.editMessage(me).queue();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}