/**
 * Created by Lemur on 05.03.2018.
 */

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Parser {

    public static void main(String[] args) throws IOException {

        String orig = args[0];
        String diff = args[1];
        String parm = args[2];
        System.out.println(orig);
        System.out.println(diff);
        System.out.println(parm);
        try {
            File file = new File("C:/IRU3.Main.output.sid");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            String par = "output";
            Set<String> ss = new HashSet<>();
            String pattern = par + "::(\\w+) =";
            while (line != null) {
                //System.out.println(line);
                Pattern r = Pattern.compile(pattern);
                Matcher m = r.matcher(line);
                while (m.find()) ss.add(m.group(1));
                line = reader.readLine();
            }
            System.out.println(ss);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
