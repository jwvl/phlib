package eu.jwvl.phonlib.constraint.candidate;

/**
 * Created by janwillem on 03/10/15.
 */
public interface Candidate {
    String getInputAsString();
    String getOutputAsString();
    SimpleCandidate toSimpleCandidate();
}
