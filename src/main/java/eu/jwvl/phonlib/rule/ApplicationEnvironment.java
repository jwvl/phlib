package eu.jwvl.phonlib.rule;

import eu.jwvl.phonlib.representation.segment.PhonologicalUnit;

import java.util.List;

/**
 * Created by janwillem on 04/10/15.
 */
public class ApplicationEnvironment {
    private final List<PhonologicalUnit> leftFeatures;
    private final PhonologicalUnit target;
    private final List<PhonologicalUnit> rightFeatures;
    private List<PhonologicalUnit> completeEnvironment;


    public ApplicationEnvironment(List<PhonologicalUnit> leftFeatures, PhonologicalUnit target, List<PhonologicalUnit> rightFeatures) {
        this.leftFeatures = leftFeatures;
        this.target = target;
        this.rightFeatures = rightFeatures;
    }


}
