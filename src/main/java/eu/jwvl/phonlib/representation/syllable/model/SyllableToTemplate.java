package eu.jwvl.phonlib.representation.syllable.model;

import com.google.common.collect.Maps;
import com.google.common.io.Resources;
import eu.jwvl.phonlib.representation.segment.StringPhoneMap;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * Created by janwillem on 07/04/2017.
 */
public class SyllableToTemplate {
    Map<String,String> mappings;
    private static String vowels = "aAeE@iIoOKLMuy|})(<>!*";

    public SyllableToTemplate(String mappingsPath) {
        mappings = Maps.newHashMap();
        URL url = Resources.getResource(mappingsPath);
        try {
            List<String> lines = Resources.readLines(url, Charset.defaultCharset());
            for (String line: lines) {
                String[] parts = line.split("\t");
                if (parts.length >1) {
                    mappings.put(parts[0],parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getAsTemplate(String input) {
        if (input.isEmpty()) {
            return "---------";
        }
        int vowelIndex = getVowelIndex(input);
        if (vowelIndex < 0) {
            return "---------";
        }
        String onset = "["+input.substring(0,vowelIndex);
        String nucleus = input.substring(vowelIndex,vowelIndex+1);
        String coda = input.substring(vowelIndex+1)+"]";
        String result = mappings.get(onset)+mappings.get(nucleus)+mappings.get(coda);
        if (result.contains("null")) {
            System.err.println("Some mapping not found in input "+input);
            return input;
        }
        return result;
    }

    public String[] getAsParts(String input) {
        String[] result = new String[0];
        if (input.isEmpty()) {
            return result;
        }
        int vowelIndex = getVowelIndex(input);
        if (vowelIndex < 0) {
            return result;
        }
        String onset = "["+input.substring(0,vowelIndex);
        String nucleus = input.substring(vowelIndex,vowelIndex+1);
        String coda = input.substring(vowelIndex+1)+"]";
        if (onset == null || nucleus == null || coda == null) {
            return result;
        }
        result = new String[3];
            result[0] = mappings.get(onset);
            result[1] = mappings.get(nucleus);
            result[2] = mappings.get(coda);

        return result;
    }




    private static int getVowelIndex(String string) {
        for (int i=0; i < string.length(); i++) {
            if (vowels.indexOf(string.charAt(i)) >= 0) {
                return i;
            }
        }
        System.err.println("No vowel found in "+string);
        return -1;
    }

    public byte[] getAsBytearray(String[] syllables, StringPhoneMap stringPhoneMap) {
        int templateLength = 9;
        byte[] result = new byte[templateLength*syllables.length];
        for (int i=0;i < syllables.length;i++) {
            int byteIndex = i*templateLength;
            String templated = getAsTemplate(syllables[i]);
            for (int j=0; j < templated.length() && j < templateLength; j++) {
                String charString = String.valueOf(templated.charAt(j));
                byte value =stringPhoneMap.getPhone(charString).getByteValue();
                result[byteIndex+j] = value;
            }
        }
        return result;
    }

    public byte[][] getAsByteMatrix(String[] syllables, StringPhoneMap stringPhoneMap) {
        int templateLength = 9;
        byte[][] result = new byte[templateLength*3][];
        for (int i=0;i < syllables.length;i++) {
            int byteIndex = i*3;
            String[] templated = getAsParts(syllables[i]);
            for (int j=0; j < templated.length && j < 3; j++) {
                String charString = templated[j];
                byte[] value =stringPhoneMap.encodeAsBytes(charString);
                result[byteIndex+j] = value;
            }
        }
        return result;
    }


}
