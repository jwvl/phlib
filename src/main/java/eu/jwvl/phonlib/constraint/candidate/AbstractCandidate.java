package eu.jwvl.phonlib.constraint.candidate;

/**
 * Created by janwillem on 24/03/16.
 */
public abstract class AbstractCandidate<I,O> implements Candidate {
    private final I input;
    private final O output;

    public AbstractCandidate(I input, O output) {
        this.input = input;
        this.output = output;
    }

    public I getInput() {
        return input;
    }
    public O getOutput() {
        return output;
    }

    @Override
    public String getInputAsString() {
        return input.toString();
    }

    @Override
    public String getOutputAsString() {
        return output.toString();
    }

    @Override
    public SimpleCandidate toSimpleCandidate() {
        return new SimpleCandidate(getInputAsString(),getOutputAsString());
    }
}
