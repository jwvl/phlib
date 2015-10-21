package eu.jwvl.phonlib.constraint.candidate;

/**
 * Created by janwillem on 03/10/15.
 */
public interface Candidate<I,O> {
    I getInput();
    O getOutput();
    SimpleCandidate toSimpleCandidate();
}
