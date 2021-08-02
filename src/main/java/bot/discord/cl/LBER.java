package bot.discord.cl;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.util.ArrayList;

public class LBER {

    public Message m;
    public ArrayList<String> leaderboard;
    public MessageEmbed me;
    public Member author;
    public int page = 1;
    public static String fastBack = "⏪";
    public static String fastForward = "⏩";
    public static String rightArrow = "➡";
    public static String leftArrow = "⬅";

    public LBER(Message mes, ArrayList<String> lb, MessageEmbed mese, Member au) {
        m = mes; leaderboard = lb; me = mese; author = au;
    }

}
