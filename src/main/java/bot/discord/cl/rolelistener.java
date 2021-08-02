package bot.discord.cl;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class rolelistener extends ListenerAdapter {

    @Override
    public void onGuildMessageReactionAdd(@NotNull GuildMessageReactionAddEvent event) {
        Role buynsell = event.getGuild().getRoleById(870389924388548629l);
        Role techtalk = event.getGuild().getRoleById(870389870244274226l);
        Role giveawayevents = event.getGuild().getRoleById(871140971138666506l);
        Role streamnotif = event.getGuild().getRoleById(870408433613037600l);
        Role black = event.getGuild().getRoleById(870432483655708762l);
        Role orange = event.getGuild().getRoleById(870420270719074346l);
        Role blue = event.getGuild().getRoleById(870420122865664051l);
        Role yellow = event.getGuild().getRoleById(870420193283829782l);
        Role green = event.getGuild().getRoleById(870420154188701696l);
        Role purple = event.getGuild().getRoleById(870419335515078737l);
        Role teal = event.getGuild().getRoleById(870418978739224606l);
        Role red = event.getGuild().getRoleById(870418913538756619l);

        if(event.getChannel().getIdLong() == 870400483091697715l) {
            Role verified = event.getGuild().getRoleById(801600810848616519l);
            if(event.getMessageIdLong() == 871533365415792720l) {
                if(event.getReactionEmote().getEmoji().equalsIgnoreCase("✅")) {
                    event.getGuild().addRoleToMember(event.getMember(), verified).queue();
                    event.getChannel().removeReactionById(871533365415792720l, "✅", event.getUser()).queue();
                }
            }
        }

        if(event.getChannel().getIdLong() != 870386711945969666l) return;
        if(event.getMessageIdLong() == 871509845008859169l) {
            if(event.getReactionEmote().getEmoji().equalsIgnoreCase("\uD83D\uDCB0")) event.getGuild().addRoleToMember(event.getMember(), buynsell).queue();
            if(event.getReactionEmote().getEmoji().equalsIgnoreCase("\uD83D\uDDA5")) event.getGuild().addRoleToMember(event.getMember(), techtalk).queue();
            if(event.getReactionEmote().getEmoji().equalsIgnoreCase("\uD83C\uDF89")) event.getGuild().addRoleToMember(event.getMember(), giveawayevents).queue();
        }
        if(event.getMessageIdLong() == 871509845742846002l) {
            if(event.getReactionEmote().getEmoji().equalsIgnoreCase("\uD83D\uDC4D")) event.getGuild().addRoleToMember(event.getMember(), streamnotif).queue();
        }
        if(event.getMessageIdLong() == 871509846061641768l) {
            HashMap<Long, String> idUnicode = new HashMap<>();
            idUnicode.put(870432483655708762l, "⚫"); idUnicode.put(870420270719074346l, "\uD83D\uDFE0"); idUnicode.put(870420122865664051l, "\uD83D\uDD35"); idUnicode.put(870420193283829782l, "\uD83D\uDFE1"); idUnicode.put(870420154188701696l, "\uD83D\uDFE2"); idUnicode.put(870419335515078737l, "\uD83D\uDFE3"); idUnicode.put(870418978739224606l, "\uD83C\uDF10"); idUnicode.put(870418913538756619l, "\uD83D\uDD34");
            for(Role r : event.getMember().getRoles()) {
                if(idUnicode.containsKey(r.getIdLong())) {
                    event.getGuild().removeRoleFromMember(event.getMember(), r).queue();
                    try { event.getChannel().removeReactionById(871509846061641768l, idUnicode.get(r.getIdLong()), event.getUser()).queue(); } catch(Exception e) {}

                }
            }
            if(event.getReactionEmote().getEmoji().equalsIgnoreCase("⚫")) event.getGuild().addRoleToMember(event.getMember(), black).queue();
            if(event.getReactionEmote().getEmoji().equalsIgnoreCase("\uD83D\uDFE0")) event.getGuild().addRoleToMember(event.getMember(), orange).queue();
            if(event.getReactionEmote().getEmoji().equalsIgnoreCase("\uD83D\uDD35")) event.getGuild().addRoleToMember(event.getMember(), blue).queue();
            if(event.getReactionEmote().getEmoji().equalsIgnoreCase("\uD83D\uDFE1")) event.getGuild().addRoleToMember(event.getMember(), yellow).queue();
            if(event.getReactionEmote().getEmoji().equalsIgnoreCase("\uD83D\uDFE2")) event.getGuild().addRoleToMember(event.getMember(), green).queue();
            if(event.getReactionEmote().getEmoji().equalsIgnoreCase("\uD83D\uDFE3")) event.getGuild().addRoleToMember(event.getMember(), purple).queue();
            if(event.getReactionEmote().getEmoji().equalsIgnoreCase("\uD83C\uDF10")) event.getGuild().addRoleToMember(event.getMember(), teal).queue();
            if(event.getReactionEmote().getEmoji().equalsIgnoreCase("\uD83D\uDD34")) event.getGuild().addRoleToMember(event.getMember(), red).queue();
        }
    }

    @Override
    public void onGuildMessageReactionRemove(@NotNull GuildMessageReactionRemoveEvent event) {
        Role buynsell = event.getGuild().getRoleById(870389924388548629l);
        Role techtalk = event.getGuild().getRoleById(870389870244274226l);
        Role giveawayevents = event.getGuild().getRoleById(871140971138666506l);
        Role streamnotif = event.getGuild().getRoleById(870408433613037600l);
        Role black = event.getGuild().getRoleById(870432483655708762l);
        Role orange = event.getGuild().getRoleById(870420270719074346l);
        Role blue = event.getGuild().getRoleById(870420122865664051l);
        Role yellow = event.getGuild().getRoleById(870420193283829782l);
        Role green = event.getGuild().getRoleById(870420154188701696l);
        Role purple = event.getGuild().getRoleById(870419335515078737l);
        Role teal = event.getGuild().getRoleById(870418978739224606l);
        Role red = event.getGuild().getRoleById(870418913538756619l);

        if(event.getChannel().getIdLong() != 870386711945969666l) return;
        if(event.getMessageIdLong() == 871509845008859169l) {
            if(event.getReactionEmote().getEmoji().equalsIgnoreCase("\uD83D\uDCB0")) event.getGuild().removeRoleFromMember(event.getMember(), buynsell).queue();
            if(event.getReactionEmote().getEmoji().equalsIgnoreCase("\uD83D\uDDA5")) event.getGuild().removeRoleFromMember(event.getMember(), techtalk).queue();
            if(event.getReactionEmote().getEmoji().equalsIgnoreCase("\uD83C\uDF89")) event.getGuild().removeRoleFromMember(event.getMember(), giveawayevents).queue();
        }
        if(event.getMessageIdLong() == 871509845742846002l) {
            if(event.getReactionEmote().getEmoji().equalsIgnoreCase("\uD83D\uDC4D")) event.getGuild().removeRoleFromMember(event.getMember(), streamnotif).queue();
        }
        if(event.getMessageIdLong() == 871509846061641768l) {
            if(event.getReactionEmote().getEmoji().equalsIgnoreCase("⚫")) event.getGuild().removeRoleFromMember(event.getMember(), black).queue();
            if(event.getReactionEmote().getEmoji().equalsIgnoreCase("\uD83D\uDFE0")) event.getGuild().removeRoleFromMember(event.getMember(), orange).queue();
            if(event.getReactionEmote().getEmoji().equalsIgnoreCase("\uD83D\uDD35")) event.getGuild().removeRoleFromMember(event.getMember(), blue).queue();
            if(event.getReactionEmote().getEmoji().equalsIgnoreCase("\uD83D\uDFE1")) event.getGuild().removeRoleFromMember(event.getMember(), yellow).queue();
            if(event.getReactionEmote().getEmoji().equalsIgnoreCase("\uD83D\uDFE2")) event.getGuild().removeRoleFromMember(event.getMember(), green).queue();
            if(event.getReactionEmote().getEmoji().equalsIgnoreCase("\uD83D\uDFE3")) event.getGuild().removeRoleFromMember(event.getMember(), purple).queue();
            if(event.getReactionEmote().getEmoji().equalsIgnoreCase("\uD83C\uDF10")) event.getGuild().removeRoleFromMember(event.getMember(), teal).queue();
            if(event.getReactionEmote().getEmoji().equalsIgnoreCase("\uD83D\uDD34")) event.getGuild().removeRoleFromMember(event.getMember(), red).queue();
        }
    }
}
