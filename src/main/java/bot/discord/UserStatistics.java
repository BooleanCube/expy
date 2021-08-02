package bot.discord;

import bot.Constants;
import net.dv8tion.jda.api.entities.Member;
import org.apache.commons.collections4.trie.analyzer.StringKeyAnalyzer;

import java.io.*;
import java.util.StringTokenizer;

public class UserStatistics {

    public int attemptedTrivia = 0;
    public int correctTrivia = 0;
    public int wrongTrivia = 0;
    public int xpGainedFromTrivia = 0;
    public int longestStreak = 0;
    public int currentStreak = 0;

    //Constructor
    public UserStatistics(int at, int ct, int wt, int xgft, int ls, int cs) {
        attemptedTrivia = at; correctTrivia = ct; wrongTrivia = wt; xpGainedFromTrivia = xgft; longestStreak = ls; currentStreak = cs;
    }

    //Calculate right to wrong ratio
    public static double ratioCalculation(int right, int wrong) {
        if(wrong==0) ++wrong;
        double cwr = (double)right/wrong;
        return Math.round(cwr*100.0)/100.0;
    }

    //Collecting Data From Database and stores it in a UserStatistics Object for use
    //Returns UserStatistics Object
    public static UserStatistics retrieveUserStatistics(Member m) throws IOException {
        try {
            String id = m.getId();
            BufferedReader bf = new BufferedReader(new FileReader(Constants.statsDbDir));
            String input;
            int at=0; int ct=0; int wt=0; int xgft=0; int ls=0; int cs=0;
            StringBuilder sb = new StringBuilder();
            boolean cube = true;
            while((input=bf.readLine()) != null) {
                if(input.startsWith(id)) {
                    StringTokenizer st = new StringTokenizer(input);
                    st.nextToken();
                    at = Integer.parseInt(st.nextToken());
                    ct = Integer.parseInt(st.nextToken());
                    wt = Integer.parseInt(st.nextToken());
                    xgft = Integer.parseInt(st.nextToken());
                    ls = Integer.parseInt(st.nextToken());
                    cs = Integer.parseInt(st.nextToken());
                    cube = false;
                }
                sb.append(input).append("\n");
            }
            if(cube) sb.append(m.getId()).append(" 0\n");
            FileWriter fw = new FileWriter(Constants.statsDbDir);
            fw.write(sb.toString());
            fw.close();
            return new UserStatistics(at, ct, wt, xgft, ls, cs);
        } catch(Exception e) {
            return new UserStatistics(0, 0, 0, 0, 0, 0);
        }
    }

    public static void updateDatabase(Member m, UserStatistics u) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader(Constants.statsDbDir));
        StringBuilder sb = new StringBuilder();
        String input;
        boolean cube = true;
        while((input=bf.readLine()) != null) {
            if(input.startsWith(m.getId())) {
                sb.append(m.getId() + " " + u.attemptedTrivia + " " + u.correctTrivia + " " + u.wrongTrivia + " " + u.xpGainedFromTrivia + " " + u.longestStreak + " " + u.currentStreak + "\n");
                cube = false;
            }
            else sb.append(input+"\n");
        }
        if(cube) {
            sb.append(m.getId() + " " + u.attemptedTrivia + " " + u.correctTrivia + " " + u.wrongTrivia + " " + u.xpGainedFromTrivia + " " + u.longestStreak + " " + u.currentStreak + "\n");
        }
        FileWriter fw = new FileWriter(Constants.statsDbDir);
        fw.write(sb.toString());
        fw.close();
    }

    public void increaseStreak() {
        currentStreak++;
        if(currentStreak > longestStreak) longestStreak = currentStreak;
    }

}
