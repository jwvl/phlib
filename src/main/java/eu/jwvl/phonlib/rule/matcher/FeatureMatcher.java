//package eu.jwvl.phonlib.rule.matcher;
//
//import eu.jwvl.phonlib.feature.bundle.FeatureBundle;
//import eu.jwvl.phonlib.representation.segment.FeaturePhonologicalUnit;
//import eu.jwvl.phonlib.representation.structure.LinearForm;
//import eu.jwvl.phonlib.util.numeric.Range;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by janwillem on 03/12/15.
// */
//public class FeatureMatcher implements Matcher<FeaturePhonologicalUnit> {
//    private final FeatureBundle[] bundles;
//
//    public FeatureMatcher(FeatureBundle[] featureBundle) {
//        this.bundles = featureBundle;
//    }
//
//    @Override
//    public Range[] getMatches(LinearForm<FeaturePhonologicalUnit> form) {
//        List<Range> result = new ArrayList<Range>(form.length());
//        int targetLength = form.length();
//        int patternLength = bundles.length;
////        //FeatureBundle targetBundle = featurePhonologicalUnit.getFeatures();
////        for (Feature feature: bundles) {
////            if (!targetBundle.hasFeature(feature))
////
////        }
//        return result.toArray(new Range[result.size()]);
//    }
//}
