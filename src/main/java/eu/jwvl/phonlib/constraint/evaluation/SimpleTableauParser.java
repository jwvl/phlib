package eu.jwvl.phonlib.constraint.evaluation;

import com.google.common.base.Charsets;
import eu.jwvl.phonlib.constraint.candidate.SimpleCandidate;
import eu.jwvl.phonlib.constraint.harmony.ViolationMethods;
import eu.jwvl.phonlib.constraint.hierarchy.ListingConstraint;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by janwillem on 04/10/15.
 */
public class SimpleTableauParser {
    private CSVParser parser;
    private List<CSVRecord> records;
    private String input;

    private SimpleTableauParser(CSVParser parser) {
        this.parser = parser;
        try {
            records = parser.getRecords();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SimpleTableau buildTableau() {
        List<ListingConstraint> constraintList = parseConstraints();
        List<SimpleCandidate> candidateList = new ArrayList<SimpleCandidate>();
        for (CSVRecord record: records) {
            String outputString = record.get(0);
            SimpleCandidate candidate = new SimpleCandidate(input,outputString);
            for (ListingConstraint listingConstraint: constraintList) {
                String violationString = record.get(listingConstraint.getName());
                int numViolations = ViolationMethods.violationsFromString(violationString);
                listingConstraint.addCandidateWithViolations(candidate,numViolations);
            }
            candidateList.add(candidate);
        }
        ListingConstraint[] constraintArray = constraintList.toArray(new ListingConstraint[constraintList.size()]);
        SimpleCandidate[] candidateArray = candidateList.toArray(new SimpleCandidate[candidateList.size()]);
        SimpleTableau result = new SimpleTableau(input, candidateArray, constraintArray);
        return result;
    }

    private List<ListingConstraint> parseConstraints() {
        List<ListingConstraint> constraints = new ArrayList<ListingConstraint>();
        Iterator<String> columnNames = parser.getHeaderMap().keySet().iterator();
        input = columnNames.next();
        while (columnNames.hasNext()) {
            ListingConstraint next = ListingConstraint.createFromString(columnNames.next());
            constraints.add(next);
        }
        return constraints;
    }

    public static SimpleTableau parseFromFile(String filePath) {
        CSVFormat format = determineFormat(filePath);

        ClassLoader classLoader = SimpleTableauParser.class.getClassLoader();
        String fileString = classLoader.getResource(""+filePath).getFile();
        File text = new File(fileString);
        System.out.println(text.exists());
        CSVParser parser = null;
        try {
            parser = CSVParser.parse(text, Charsets.UTF_8, format);
        } catch (IOException e) {
            System.err.println("File not found!");
            e.printStackTrace();
            return null;
        }
        SimpleTableauParser simpleTableauParser = new SimpleTableauParser(parser);
        return simpleTableauParser.buildTableau();
    }

    private static CSVFormat determineFormat(String filePath) {
        int dotIndex = filePath.lastIndexOf(".");
        if (dotIndex >= 0) {
            String extension = filePath.substring(dotIndex+1);
            if (extension.equals("xls") || extension.equals("xlsx")) {
                return CSVFormat.EXCEL.withHeader();
            } else if (extension.equals("csv") || extension.equals("txt")) {
                return CSVFormat.DEFAULT.withHeader();
            }
        }
        return CSVFormat.DEFAULT.withHeader();
    }


}
