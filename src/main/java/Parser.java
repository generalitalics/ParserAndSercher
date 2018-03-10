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
        try {
            File file = new File(orig);
            File file_diff = new File(diff);
            //"C:/IRU3.Main.output.sid"

            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            Set<String> ss = new HashSet<>();

            FileReader fr_diff = new FileReader(file_diff);
            BufferedReader reader_diff = new BufferedReader(fr_diff);
            String line_diff = reader_diff.readLine();
            Set<String> ss_diff = new HashSet<>();

            String pattern = parm + "::(\\w+) =";
            while (line != null) {
                Pattern r = Pattern.compile(pattern);
                Matcher m = r.matcher(line);
                while (m.find()) ss.add(m.group(1));
                line = reader.readLine();
            }
            while (line_diff != null) {
                Pattern r_diff = Pattern.compile(pattern);
                Matcher m_diff = r_diff.matcher(line_diff);
                while (m_diff.find()) ss_diff.add(m_diff.group(1));
                line_diff = reader_diff.readLine();
            }
            //System.out.println(ss_diff.removeAll(ss));
            System.out.println(ss_diff);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
