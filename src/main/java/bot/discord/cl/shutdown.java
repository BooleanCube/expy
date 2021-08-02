package bot.discord.cl;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class shutdown extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        String msg = event.getMessage().getContentRaw();
        if(msg.equalsIgnoreCase("e!shutdown") && event.getAuthor().getIdLong() == 525126007330570259l) {
            event.getJDA().shutdown();
            System.exit(0);
        }
    }
}
