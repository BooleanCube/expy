package bot.discord;

import bot.Constants;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Tools {

    public static String getRatioRanking(Guild g, Member m) throws IOException {
        ArrayList<Double> topcwr = new ArrayList<>();
        HashMap<Double, ArrayList<Member>> ppl = new HashMap<>();
        BufferedReader bf = new BufferedReader(new FileReader(Constants.statsDbDir));
        String input;
        String out = "";
        double mcwr = 0.0;
        StringBuilder sb = new StringBuilder();
        while((input=bf.readLine()) != null) {
            sb.append(input + "\n");
            Member me = g.getMemberById(input.split(" ")[0]);
            if(me==null) continue;
            double cwr = UserStatistics.ratioCalculation(Integer.parseInt(input.split(" ")[2]), Integer.parseInt(input.split(" ")[3]));
            if(m == me) mcwr = cwr;
            if(!topcwr.contains(cwr)) topcwr.add(cwr);
            ArrayList<Member> n = new ArrayList<>(); n.add(me);
            if(ppl.containsKey(cwr)) ppl.get(cwr).add(me);
            else ppl.put(cwr, n);
        }
        Collections.sort(topcwr); Collections.reverse(topcwr);
        boolean bottom = true;
        for(int i=1; i<=10; i++) {
            if(i>ppl.size()) break;
            while(ppl.get(topcwr.get(i-1)).size()>0) {
                if(ppl.get(topcwr.get(i-1)).get(0) == m) { out += "**" + i + ") " + ppl.get(topcwr.get(i-1)).remove(0).getEffectiveName() + " (CWR: " + topcwr.get(i-1) + ")**\n"; bottom = false; }
                else out += i + ") " + ppl.get(topcwr.get(i-1)).remove(0).getEffectiveName() + " (CWR: " + topcwr.get(i-1) + ")\n";
            }
        }
        if(bottom) out += ".\n.\n.\n**" + (topcwr.indexOf(mcwr)+1) + ") " + m.getEffectiveName() + " (CWR: " + mcwr + ")**\n";
        FileWriter fw = new FileWriter(Constants.statsDbDir);
        fw.write(sb.toString());
        fw.flush(); fw.close();
        return out;
    }

    public static String getStreakRanking(Guild g, Member m) throws IOException {
        ArrayList<Integer> topls = new ArrayList<>();
        HashMap<Integer, ArrayList<Member>> ppl = new HashMap<>();
        HashMap<Member, Integer> mcs = new HashMap<>();
        BufferedReader bf = new BufferedReader(new FileReader(Constants.statsDbDir));
        String input;
        String out = "";
        int mls = 0;
        int mecs = 0;
        StringBuilder sb = new StringBuilder();
        while((input=bf.readLine()) != null) {
            sb.append(input + "\n");
            Member me = g.getMemberById(input.split(" ")[0]);
            if(me==null) continue;
            int ls = Integer.parseInt(input.split(" ")[5]);
            int cs = Integer.parseInt(input.split(" ")[6]);
            if(m == me) { mls = ls; mecs = cs; }
            if(!topls.contains(ls)) topls.add(ls);
            mcs.put(me, cs);
            ArrayList<Member> n = new ArrayList<>(); n.add(me);
            if(ppl.containsKey(ls)) ppl.get(ls).add(me);
            else ppl.put(ls, n);
        }
        Collections.sort(topls); Collections.reverse(topls);
        boolean bottom = true;
        for(int i=1; i<=10;) {
            if(i>ppl.size()) break;
            while(ppl.get(topls.get(i-1)).size()>0) {
                Member me = ppl.get(topls.get(i-1)).get(0);
                if(ppl.get(topls.get(i-1)).get(0) == m) { out += "**" + i + ") " + ppl.get(topls.get(i-1)).remove(0).getEffectiveName() + " (`LS=" + topls.get(i-1) + " | CS=" + mcs.get(me) + "`)**\n"; bottom = false; }
                else out += i + ") " + ppl.get(topls.get(i-1)).remove(0).getEffectiveName() + " (`LS=" + topls.get(i-1) + " | CS=" + mcs.get(me) + "`)\n";
            }
            ++i;
        }
        if(bottom) out += ".\n.\n.\n**" + (topls.indexOf(mls)+1) + ") " + m.getEffectiveName() + "(`LS=" + mls + " | CS=" + mecs + "`)**\n";
        FileWriter fw = new FileWriter(Constants.statsDbDir);
        fw.write(sb.toString());
        fw.flush(); fw.close();
        return out;
    }

    public static ArrayList<String> getLeaderBoard(Guild g, Member mem) throws IOException {
        ArrayList<String> ret = new ArrayList<>();
        BufferedReader bf = new BufferedReader(new FileReader(Constants.dbDir));
        HashMap<Integer, ArrayList<Member>> people = new HashMap<>();
        ArrayList<Integer> xp = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        String input;
        while((input=bf.readLine()) != null) {
            Member m = g.getMemberById(input.split(" ")[0]);
            if(!xp.contains(Integer.parseInt(input.split(" ")[1]))) xp.add(Integer.parseInt(input.split(" ")[1]));
            ArrayList<Member> newList = new ArrayList<>(); newList.add(m);
            if(people.containsKey(Integer.parseInt(input.split(" ")[1]))) people.get(Integer.parseInt(input.split(" ")[1])).add(m);
            else people.put(Integer.parseInt(input.split(" ")[1]), newList);
        }
        Collections.sort(xp); Collections.reverse(xp);
//        int rankExcess = 0;
        int lines = 1;
        for(int i=1; i<=xp.size();) {
            if(people.get(xp.get(i-1)).size()>=1) {
                if(people.get(xp.get(i-1)).get(0) == null || people.get(xp.get(i-1)).get(0).getUser() == null) { people.get(xp.get(i-1)).remove(0); continue; }
                if(people.get(xp.get(i-1)).get(0) == mem) sb.append("**");
                sb.append(i).append(". ");
                sb.append(people.get(xp.get(i-1)).get(0).getUser().getAsTag()).append(" (XP: ").append(getXP(people.get(xp.get(i-1)).get(0))).append(")\n");
                if(people.get(xp.get(i-1)).get(0) == mem) sb.append("**");
                people.get(xp.get(i-1)).remove(0);
                ++lines;
                //++rankExcess;
            }
            else if(i==xp.size() && !sb.toString().equals("")) { ret.add(sb.toString()); ++i; }
            else ++i;
            /*-- rankExcess;*/
            if(lines%11==0) {
                ret.add(sb.toString()); sb = new StringBuilder(""); ++lines;
            }
        }
        return ret;
    }

    public static void addXP(Member m, int XP) throws IOException {
        long id = m.getIdLong();
        BufferedReader bf = new BufferedReader(new FileReader(Constants.dbDir));
        StringBuilder sb = new StringBuilder();
        String input;
        boolean cube = true;
        while((input=bf.readLine()) != null) {
            if(input.startsWith(m.getId())) {
                int p = Integer.parseInt(input.split(" ")[1]) + XP;
                if(p>=0) sb.append(m.getId() + " " + p + "\n");
                else sb.append(m.getId() + " " + 0 + "\n");
                cube = false;
            }
            else sb.append(input+"\n");
        }
        if(cube) {
            if(XP >= 0) sb.append(m.getId() + " " + XP + "\n");
            else sb.append(m.getId() + " " + 0 + "\n");
        }
        FileWriter fw = new FileWriter(Constants.dbDir);
        fw.write(sb.toString());
        fw.close();
    }

    public static int getXP(Member m) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader(Constants.dbDir));
        String input;
        int xp = 0;
        StringBuilder sb = new StringBuilder();
        boolean cube = true;
        while((input=bf.readLine()) != null) {
            if(input.startsWith(m.getId())) {
                xp = Integer.parseInt(input.split(" ")[1]);
                cube = false;
            }
            sb.append(input).append("\n");
        }
        if(cube) sb.append(m.getId()).append(" 0\n");
        FileWriter fw = new FileWriter(Constants.dbDir);
        fw.write(sb.toString());
        fw.close();
        return xp;
    }

    public static Member getEffectiveMember(Guild g, String s) {
        Member m = null;
        try {
            long id = Long.parseLong(s.replaceAll("[<@!>]",""));
            m = g.getMemberById(id);
        } catch(Exception e) {
            try {
                m = g.getMemberByTag(s);
            } catch(Exception e2) {
                try {
                    m = g.getMembersByEffectiveName(s, true).get(0);
                } catch(Exception e3) {
                    try {
                        m = g.getMembersByName(s, true).get(0);
                    } catch(Exception e4) {
                        return null;
                    }
                }
            }
        }
        return m;
    }

    public static int getLevelFromXP(int xp) {
        int a = 500;
        int xp2 = xp;
        int level = 0;
        while(true) {
            xp2-=a;
            a+=55;
            if(xp2<0) break;
            ++level;
        }
        return level;
    }

    public static int getXPFromLevel(int level) {
        int xp = 0;
        int a = 500;
        for(int i=0; i<level; i++) {
            xp+=a;
            a+=55;
        }
        return xp;
    }

    public static int getRank(Member m, Guild g) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader(Constants.dbDir));
        HashMap<Integer, ArrayList<Member>> people = new HashMap<>();
        ArrayList<Integer> xp = new ArrayList<>();
        String input;
        while((input=bf.readLine()) != null) {
            Member me = g.getMemberById(input.split(" ")[0]);
            if(!xp.contains(Integer.parseInt(input.split(" ")[1]))) xp.add(Integer.parseInt(input.split(" ")[1]));
            ArrayList<Member> newList = new ArrayList<>(); newList.add(me);
            if(people.containsKey(Integer.parseInt(input.split(" ")[1]))) people.get(Integer.parseInt(input.split(" ")[1])).add(me);
            else people.put(Integer.parseInt(input.split(" ")[1]), newList);
        }
        Collections.sort(xp); Collections.reverse(xp);
        //int rankExcess = 0;
        for(int i=0; i<xp.size();) {
            if(people.get(xp.get(i)).size()>=1) {
                if(people.get(xp.get(i)).get(0) == null) { people.get(xp.get(i)).remove(0); continue; }
                if(people.get(xp.get(i)).get(0).getIdLong() == m.getIdLong()) return i+1/*+rankExcess*/;
                people.get(xp.get(i)).remove(0);
                //++rankExcess;
            }
            else { ++i; /*--rankExcess;*/ }
        }
        return -1;
    }

    public static String secondsToTime(long timeseconds) {
        StringBuilder builder = new StringBuilder();
        int years = (int)(timeseconds / (60*60*24*365));
        if(years>0)
        {
            builder.append(years).append(" years, ");
            timeseconds = timeseconds % (60*60*24*365);
        }
        int weeks = (int)(timeseconds / (60*60*24*365));
        if(weeks>0)
        {
            builder.append(weeks).append(" weeks, ");
            timeseconds = timeseconds % (60*60*24*7);
        }
        int days = (int)(timeseconds / (60*60*24));
        if(days>0)
        {
            builder.append(days).append(" days, ");
            timeseconds = timeseconds % (60*60*24);
        }
        int hours = (int)(timeseconds / (60*60));
        if(hours>0)
        {
            builder.append(hours).append(" hours, ");
            timeseconds = timeseconds % (60*60);
        }
        int minutes = (int)(timeseconds / (60));
        if(minutes>0)
        {
            builder.append(minutes).append(" minutes, ");
            timeseconds = timeseconds % (60);
        }
        if(timeseconds>0)
            builder.append(timeseconds).append(" seconds");
        String str = builder.toString();
        if(str.endsWith(", "))
            str = str.substring(0,str.length()-2);
        if(str.equals(""))
            str="No time";
        return str;
    }

    public static int getRandXP() {
        return (int)(Math.random()*11)+5;
    }

}
