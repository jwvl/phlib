package eu.jwvl.phonlib.io.ngram;

import com.google.common.base.Charsets;
import com.google.common.collect.Sets;
import com.google.common.io.Resources;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Created by janwillem on 15/07/2017.
 * Filter long list of bigrams to keep only items w/ clitics
 */
public class FilterBigrams {
    private final static String CLITICS_FILE = "data/clitics.txt";
    private final static String BIGRAMS_FILE = "data/bigrams-filtered.txt";
    private final static String FILTERED_OUT = "out/bigrams-filtered-clitics.txt";
    private static Set<String> clitics;

    public static void main(String[] args) {
        clitics = Sets.newHashSet();
        URL bigramsUrl = Resources.getResource(BIGRAMS_FILE);
        URL cliticsUrl = Resources.getResource(CLITICS_FILE);
        String path = bigramsUrl.getPath();
        try {
            List<String> cliticLines = Resources.readLines(cliticsUrl, Charsets.UTF_8);
            for (String cliticLine: cliticLines) {
                String[] parts = cliticLine.split("\t");
                if (parts.length > 1) {
                    clitics.add(parts[0]);
                }
            }
            Stream<String> lines = Files.lines(Paths.get(path));
           // lines.filter(line -> hasClitic(line)) Finish later

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean hasClitic(String line) {
        String[] parts = line.split("\t");
        String[] words = parts[0].split(" ");
        if (words.length < 2) {
            return false;
        }
        return (clitics.contains(words[0]) || clitics.contains(words[1]));
    }
}
