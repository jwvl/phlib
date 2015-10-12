package eu.jwvl.phonlib.constraint.evaluation;

import eu.jwvl.phonlib.constraint.candidate.SimpleCandidate;
import eu.jwvl.phonlib.constraint.harmony.ViolationMethods;
import eu.jwvl.phonlib.constraint.hierarchy.ListingConstraint;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        return new SimpleTableau(input, candidateArray, constraintArray);
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

    public static SimpleTableau parseFromFile(String filePath, CSVFormat format) {
        InputStream in = SimpleTableauParser.class.getResourceAsStream(filePath);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        CSVParser parser = null;
        try {
            parser = new CSVParser(bufferedReader, format);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        SimpleTableauParser simpleTableauParser = new SimpleTableauParser(parser);
        return simpleTableauParser.buildTableau();
    }


}
