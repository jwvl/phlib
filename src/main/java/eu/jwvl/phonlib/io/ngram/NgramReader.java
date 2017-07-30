package eu.jwvl.phonlib.io.ngram;

import com.google.common.base.Charsets;
import com.google.common.collect.Sets;
import com.google.common.io.Resources;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by janwillem on 30/06/2017.
 */
public class NgramReader {

    public static final String input ="data/2gm.txt";
    public static final String celexPhones ="data/celex-phones-4.txt";
    public static final String output="bigrams-filtered.txt";

    public static void main(String[] args) {

        Set<String> wordList = createWordList(celexPhones);
        System.out.println("Created word list");
        URL url = Resources.getResource(input);
        String path = url.getPath();

        // Create file

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            FileWriter fstream = new FileWriter(output, true);
            BufferedWriter out = new BufferedWriter(fstream);
            String line;
            int readLineCount = 0;
            int writtenLineCount = 0;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length > 1) {
                    String[] words = parts[0].split(" ");
                    if (words.length > 1) {
                        if (wordList.contains(words[0].toLowerCase()) && wordList.contains(words[1].toLowerCase())) {
                            out.write(line);
                            out.newLine();
                            writtenLineCount++;
                        }
                    }
                }
                readLineCount++;
                if (readLineCount % 100000 == 0) {
                    System.out.println("Read lines: "+readLineCount+ ", number written : "+writtenLineCount);
                }
            }
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static Set<String> createWordList(String celexPhones) {
        Set<String> result = Sets.newHashSet();
        URL url = Resources.getResource(celexPhones);
        List<String> lines = new ArrayList<>();
        try {
            lines = Resources.readLines(url, Charsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String line: lines) {
            String[] parts = line.split("\\\\");
            result.add(parts[0]);
        }
        return result;
    }
}
