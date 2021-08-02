package bot.discord.cl;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class test extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        String msg = event.getMessage().getContentRaw();
        if(msg.equalsIgnoreCase("e!sendembeds") && event.getAuthor().getIdLong() == 525126007330570259l) {
            event.getChannel().sendMessage(new EmbedBuilder()
                    .setTitle("Verification")
                    .setDescription("React with ✅ to gain access to all the text channels and voice channels in this server!\n Verification is an important process to go through to ensure you are not a bot!")
                    .setFooter("If you are not able to see text channels or voice channels even after reacting contact a Moderator in the server!")
                    .build()
            ).queue(m -> {
                m.addReaction("✅").queue();
            });
        }
    }
}
