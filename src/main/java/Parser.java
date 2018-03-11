/**
 * Created by Lemur on 05.03.2018.
 */

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.Matcher;



public class Parser {

    //наш чудесный regexp
    static String pattern = "::(\\w+) =";

    public static void main(String[] args) throws IOException {

        try {
            //чтение 2 файлов из args
            File originalFile = new File(args[0]);
            File comparedFile = new File(args[1]);

            //читаем оригинальный файл полностью и отдельно первую строку
            FileReader fr = new FileReader(originalFile);
            BufferedReader reader = new BufferedReader(fr);
            String originalLine = reader.readLine();
            Set<String> originalSet = new HashSet<>();

            //читаем сравниваемый файл полностью и отдельно первую строку
            fr = new FileReader(comparedFile);
            BufferedReader comparedReader = new BufferedReader(fr);
            String comparedLine = comparedReader.readLine();
            Set<String> comparedSet = new HashSet<>();


            //разбираем файлы на 2 set
            while (originalLine != null) {
                Matcher m = Pattern.compile(pattern).matcher(originalLine);
                while (m.find()) originalSet.add(m.group(1));
                originalLine = reader.readLine();
            }
            while (comparedLine != null) {
                Pattern r_diff = Pattern.compile(pattern);
                Matcher m_diff = r_diff.matcher(comparedLine);
                while (m_diff.find()) comparedSet.add(m_diff.group(1));
                comparedLine = comparedReader.readLine();
            }
            Set<String> originalSetCopy = originalSet;
            Set<String> comparedSetCopy = comparedSet;
            
            originalSetCopy.removeAll(comparedSet);
            Iterator iterator = originalSetCopy.iterator();
            System.out.println("[differences]:");
            while (iterator.hasNext()){
                System.out.println(iterator.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Set<String> parseFile(String file) throws IOException {
        File originalFile = new File(file);

        FileReader fr = new FileReader(originalFile);
        BufferedReader reader = new BufferedReader(fr);
        String originalLine = reader.readLine();
        Set<String> parseSet = new HashSet<>();

        while (originalLine != null) {
            Matcher m = Pattern.compile(pattern).matcher(originalLine);
            while (m.find()) parseSet.add(m.group(1));
            originalLine = reader.readLine();
        }

        return parseSet;
    }

}
