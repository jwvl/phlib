package eu.jwvl.phonlib.app;

import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.common.io.Resources;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by janwillem on 15/04/2017.
 */
public class CreateCelexFeatures {

    public static void main(String[] args) {
        String tableFile = "ipa2xsampa.txt";
        URL tableUrl = Resources.getResource(tableFile);
        String featuresFile = "features/featuresHayes-utf8.txt";
        URL featuresUrl = Resources.getResource(featuresFile);
        try {
            List<String> codes = Resources.readLines(tableUrl, Charsets.UTF_8);
            List<String> features = Resources.readLines(featuresUrl, Charsets.UTF_8);
            Map<String,String> ipaToDisc = createIpaToDiscMap(codes);
            for (String featureLine: features) {
                String rowName = featureLine.split("\t")[0];
                if (ipaToDisc.containsKey(rowName)) {
                    String disc = ipaToDisc.get(rowName);
                    String newRow = featureLine.replaceFirst(rowName,disc);
                    System.out.println(newRow);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String,String> createIpaToDiscMap(List<String> codes) {
        Map<String,String> result = Maps.newHashMap();
        for (String s: codes) {
            String[] parts = s.split("\t");
            String ipa = parts[1];
            String disc = parts[3];
            if (!disc.equals("?")) {
                result.put(ipa,disc);
            }
        }
        return result;
    }
}
