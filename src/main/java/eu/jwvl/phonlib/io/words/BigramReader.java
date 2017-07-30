package eu.jwvl.phonlib.io.words;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.common.io.Resources;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Set;

/**
 * Created by janwillem on 06/05/2017.
 */
public class BigramReader {
    private final Table<String,String,Integer> bigrams;
    private static int MIN_FREQUENCY = 50;

    public BigramReader(String file, Set<String> dictionary) {
        String path = Resources.getResource(file).getPath();
        bigrams = HashBasedTable.create();
        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            String strLine;

            //Read File Line By Line
            while ((strLine = br.readLine()) != null)   {
                String[] parts = strLine.split("\t");
                if (parts.length >= 3) {
                    String firstWord = parts[0];
                    String secondWord = parts[1];
                    int frequency = Integer.parseInt(parts[2]);
                    if (frequency >= MIN_FREQUENCY && dictionary.contains(firstWord) && dictionary.contains(secondWord)) {
                        bigrams.put(firstWord,secondWord,frequency);
                    }
                }
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

    }
}
