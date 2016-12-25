package eu.jwvl.phonlib.io;

import com.google.common.collect.Lists;

import java.io.*;
import java.util.List;

public class SimpleStringReader {


    public static List<String> StringsFromFile(String path) {
        List<String> result = Lists.newArrayList();
        String toPrint = SimpleStringReader.class.getClassLoader().getResource(path).getFile();
        System.out.println(toPrint);
        File file = new File(toPrint);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String nextLine;
            while ((nextLine = reader.readLine()) != null) {
                if ((!nextLine.isEmpty()))
                result.add(nextLine.trim());
            }
            reader.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

}
