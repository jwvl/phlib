package eu.jwvl.phonlib.representation.segment;

import eu.jwvl.phonlib.io.MyStringTable;

import java.util.*;

/**
 * Created by janwillem on 02/04/16.
 */
public class StringPhoneReader {

    private static Map<String, StringPhonologicalUnit> instances = new HashMap<>();
    private static Map<String,String> aliases = new HashMap<>();
    private static int longestString = 1;

    public static StringPhonologicalUnit getInstance(String string, Sonority sonority) {
        int numChars = string.length();
        longestString = Math.max(longestString, numChars);
        boolean hasAlias = aliases.containsKey(string);
        if (hasAlias) {
            return getInstance(aliases.get(string));
        }
        StringPhonologicalUnit found = instances.get(string);
        if (found == null) {
            found = createNew(string,sonority);
        }
        return found;

    }

    public static StringPhonologicalUnit getInstance(String string) {
        return getInstance(string, Sonority.X);

    }

    private static StringPhonologicalUnit createNew(String string, Sonority sonority) {
        System.out.println("Creating new phoneme " +string+" with sonority " +sonority);
        StringPhonologicalUnit result = new StringPhonologicalUnit(string, sonority);
        instances.put(string, result);
        return new StringPhonologicalUnit(string, sonority);
    }

    public static EncodedPhoneString parseSequence(String string) {
        List<StringPhonologicalUnit> result = new ArrayList<StringPhonologicalUnit>(string.length());
        int cursor =0;
        while (cursor < string.length()) {
            boolean found = false;
            int length = (Math.min(longestString,string.length()-cursor));
            while (length > 0 && !found) {
                String subString = string.substring(cursor,cursor+length);
                if (hasString(subString)) {
                    found = true;
                    result.add(getInstance(subString));
                    cursor+= length;
                } else {
                    length--;
                }
            }

            if (!found) {
                result.add(getInstance(string.substring(cursor,cursor+1)));
                cursor++;
            }
        }
        EncodedPhoneString encoded = EncodedPhoneString.create(result);
//        System.out.println("Found onset " + encoded);
        return encoded;

    }

    private static boolean hasString(String substring) {
        return aliases.containsKey(substring) || instances.containsKey(substring);
    }

    public static void readFromFile(String filename) {
        MyStringTable data = MyStringTable.fromFile(filename, true, ",");
        for (int i=0; i < data.getNumRows(); i++) {
            String firstCol = data.getString(i,0);
            String secondCol = data.getString(i,1);
            Sonority sonority = Sonority.valueOf(data.getString(i,2));
            if (!firstCol.equals(secondCol)) {
                addAlias(firstCol, secondCol);
            } else {
                getInstance(firstCol, sonority);
            }
        }
    }

    public static void addAlias(String fake, String real) {
        aliases.put(fake,real);
    }

    public static Collection<StringPhonologicalUnit> getForSonority(Sonority sonority) {
        Set<StringPhonologicalUnit> result = new HashSet<>();
        for (StringPhonologicalUnit unit: instances.values()) {
            if (unit.getSonority() == sonority) {
                result.add(unit);
            }
        }
        return result;
    }

    public static StringPhonologicalUnit findBestMatch(String fullString, int startAt) {
        int stopAt = Math.min(startAt+longestString,fullString.length());
        while (stopAt > startAt) {
            String substring = fullString.substring(startAt,stopAt);
            if (hasString(substring)) {
                return getInstance(substring);
            }
            stopAt--;
        }
        return null;
    }
}
