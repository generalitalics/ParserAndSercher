package ru.ruru; /**
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

    public static void main(String[] args) {
        try {
            Set<String> originalSet = parseFile(args[0]);
            Set<String> comparedSet = parseFile(args[1]);
            Set<String> originalSetCopy = new HashSet<>();
            originalSetCopy.addAll(originalSet);
            Set<String> comparedSetCopy = new HashSet<>();
            comparedSetCopy.addAll(comparedSet);

            originalSetCopy.removeAll(comparedSet);
            System.out.println("[differences]:");
            printing(originalSetCopy);

            comparedSetCopy.removeAll(originalSet);
            System.out.println("\n[added]:");
            printing(comparedSetCopy);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printing(Set<String> printSet) {
        Iterator iterator = printSet.iterator();
        if (printSet.isEmpty()) {
            System.out.println("None!");
        } else {
            while (iterator.hasNext()){
                System.out.println(iterator.next());
            }
        }
    }

    public static Set<String> parseFile(String file) throws IOException {
        Set<String> parseSet = new HashSet<>();
        //Разбираем файл
        File originalFile = new File(file);
        FileReader fr = new FileReader(originalFile);
        BufferedReader reader = new BufferedReader(fr);
        String originalLine = reader.readLine();
        //заполняем Set найденными параметрами
        while (originalLine != null) {
            Matcher m = Pattern.compile(pattern).matcher(originalLine);
            while (m.find()) parseSet.add(m.group(1));
            originalLine = reader.readLine();
        }
        return parseSet;
    }

}
