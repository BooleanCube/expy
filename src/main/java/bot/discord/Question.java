package bot.discord;

import java.util.ArrayList;

public class Question {

    public String question;
    public ArrayList<String> answers;
    public String correctAnswer;
    public int XP;

    public Question(String q, ArrayList<String> a, String ca, int p) {
        question = q.replaceAll("&amp;", "&").replaceAll("&uacute;", "u").replaceAll("&Aacute;", "A").replaceAll("&quot;", "\"").replaceAll("&eacute;", "e").replaceAll("&#039;", "'").replaceAll("&apos;", "'").replaceAll("&gt;", ">").replaceAll("&lt;", "<");
        answers = a; correctAnswer = ca; XP = p;
    }

}
