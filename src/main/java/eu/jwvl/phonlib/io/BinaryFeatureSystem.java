package eu.jwvl.phonlib.io;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import eu.jwvl.phonlib.feature.bundle.BinaryFeatureBundle;
import eu.jwvl.phonlib.feature.bundle.BinaryFeatureToBoolean;
import eu.jwvl.phonlib.feature.system.IndexedFeatureSystem;
import eu.jwvl.phonlib.representation.segment.StringPhone;
import eu.jwvl.phonlib.representation.segment.StringPhoneMap;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by janwillem on 07/04/2017.
 */
public class BinaryFeatureSystem {

    public StringPhoneMap phones;
    private boolean[][] phonesToBooleanArrays;
    private BinaryFeatureToBoolean binaryFeatureToBoolean;

    public BinaryFeatureSystem(String path) {
        this(path,"\t");
    }

    public BinaryFeatureSystem(String path, String separator) {
        URL url = Resources.getResource(path);
        try {
            List<String> rows = Resources.readLines(url, Charsets.UTF_8);
            int oldNumRows = rows.size();
            for (int i =oldNumRows-1; i >=0; i--) {
                if (rows.get(i).isEmpty() || rows.get(i).length() < 2) {
                    rows.remove(i);
                }
            }
            int numPhones = rows.size(); // cause we also add null phoneme
            phones = new StringPhoneMap();
            String firstRow = rows.get(0);
            String[] rowSplit = firstRow.split(separator);
            String[] featureNames = new String[rowSplit.length-1];
            for (int i=0; i <featureNames.length; i++) {
                featureNames[i] = rowSplit[i+1];
            }
            IndexedFeatureSystem indexedFeatureSystem = new IndexedFeatureSystem(featureNames);
            binaryFeatureToBoolean = new BinaryFeatureToBoolean(indexedFeatureSystem);
            phonesToBooleanArrays = new boolean[numPhones][];
            phones.createAndAdd("-"); // replace with null symbol later?
            phonesToBooleanArrays[0] = binaryFeatureToBoolean.encode(new BinaryFeatureBundle());
            for (int row =1; row < rows.size(); row++) {
                String rowString = rows.get(row);
                String[] phoneAndValues = rowString.split(separator);
                String phoneName = phoneAndValues[0];
                StringPhone stringPhone = phones.createAndAdd(phoneName);
                BinaryFeatureBundle bundle = new BinaryFeatureBundle();
                for (int col =1; col < phoneAndValues.length; col++) {
                    String featureName = indexedFeatureSystem.getFeatureByIndex(col-1);
                    String featureValue = phoneAndValues[col];
                    addFeatureToBundle(bundle,featureName,featureValue);
                }
                byte index = stringPhone.getByteValue();
                phonesToBooleanArrays[index] = binaryFeatureToBoolean.encode(bundle);
            }

            for (byte i=0; i < phones.size(); i++) {
                StringPhone phone = phones.getByIndex(i);
                System.out.print(phone);
                for (int j=0; j < indexedFeatureSystem.size(); j++) {
                    System.out.print("\t");
                    System.out.print(phonesToBooleanArrays[i][j]);
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void addFeatureToBundle(BinaryFeatureBundle bundle, String feature, String value) {
        if (value.equals("-")) {
            bundle.addFeature(feature,false);
        } else if (value.equals("+")) {
            bundle.addFeature(feature,true);
        }
    }

    public int numBitsPerPhone() {
        return binaryFeatureToBoolean.arrayLength();
    }

    public boolean[] phoneToBooleanArray(StringPhone phone) {
        return phonesToBooleanArrays[phone.getByteValue()];
    }

    public boolean[] phoneToBooleanArray(byte value) {
        return phoneToBooleanArray(phones.getByIndex(value));
    }
}
