package bot.discord;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class API {

    public static Question getQuestion() {
        try {
            String[] apis = {"https://opentdb.com/api.php?amount=1&category=15&type=multiple", "https://opentdb.com/api.php?amount=1&category=18&type=multiple", "https://opentdb.com/api.php?amount=1&category=18&type=multiple"};
            URL apiLink = new URL(apis[(int)(Math.random()*2)]);
            HttpsURLConnection https = (HttpsURLConnection) apiLink.openConnection();
            https.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
            BufferedReader bf = new BufferedReader(new InputStreamReader(https.getInputStream()));
            String input = bf.readLine();
            String q = input.substring(input.indexOf("\"question\":\"")+12, input.indexOf("\",\"correct_answer\":\""));
            q.replaceAll("&amp;", "&").replaceAll("&oacute;", "o").replaceAll("&quot;", "\"").replaceAll("&apos;", "'").replaceAll("&gt;", ">").replaceAll("&lt;", "<");
            String ca = input.substring(input.indexOf("\"correct_answer\":\"")+18, input.indexOf("\",\"incorrect_answers\":"));
            ArrayList<String> a = new ArrayList<>();
            a.add(ca);
            String ia = input.substring(input.indexOf("\"incorrect_answers\":[\"")+22, input.indexOf("\"]}]}"));
            for(String s : ia.split("\",\"")) a.add(s);
            String dif = input.substring(input.indexOf("\"difficulty\":\"")+14, input.indexOf("\",\"question\":"));
            int p = (int)(Math.random()*100)%51;
            if(dif.equalsIgnoreCase("hard")) p+=50;
            else if(dif.equalsIgnoreCase("medium")) p+=40;
            else p+=35;
            return new Question(q, a, ca, p);
        } catch(Exception e) {}
        return null;
    }

}
