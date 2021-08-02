package bot.discord.cl;

import bot.Constants;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class ping extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        String msg = event.getMessage().getContentRaw();
        if(msg.equalsIgnoreCase("e!ping")) {
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
                            .setTitle("Ping")
                            .setThumbnail("https://media.discordapp.net/attachments/830844105230909490/830881220190535740/830878891399970866.png?width=115&height=115")
                            .addField("Gateway Ping:", String.valueOf(event.getJDA().getGatewayPing()),false)
                            .addField("Rest Ping:", String.valueOf(event.getJDA().getRestPing().complete()), false)
                            .build()
            ).queue();
        }
    }
}
