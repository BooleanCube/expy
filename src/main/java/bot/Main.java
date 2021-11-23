package bot;

import bot.discord.Tools;
import bot.discord.cl.*;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import javax.security.auth.login.LoginException;

public class Main {

    public static void main(String[] args) throws LoginException {

        //View Channels, Manage Roles, Send Messages, Embed Links, Attach Files, add reactions, use external emoji, manage messages

        JDABuilder.createDefault("never gonna give you up").setChunkingFilter(ChunkingFilter.ALL)
                .setMemberCachePolicy(MemberCachePolicy.ALL).enableIntents(GatewayIntent.GUILD_MEMBERS)
                .setActivity(Activity.watching("you | e!help"))
                .addEventListeners(new syncxp())
                .addEventListeners(new rolelistener())
                .addEventListeners(new xplistener())
                .addEventListeners(new trivialistener())
                .addEventListeners(new leaderboardlistener())
                .addEventListeners(new trivia())
                .addEventListeners(new leaderboard())
                .addEventListeners(new stats())
                .addEventListeners(new XP())
                .addEventListeners(new ranks())
                .addEventListeners(new help())
                .addEventListeners(new ping())
                .addEventListeners(new test())
                .addEventListeners(new shutdown())
                .build();

    }

}
