package eu.jwvl.phonlib.constraint.candidate;

/**
 * Created by janwillem on 03/10/15.
 */
public interface Candidate<I,O> {
    public I getInput();
    public O getOutput();
}
