package eu.jwvl.phonlib.constraint.candidate;

/**
 * Created by janwillem on 03/10/15.
 */
public class SimpleCandidate implements Candidate<String,String> {

    private final String input;
    private final String output;

    public SimpleCandidate(String input, String output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public String getInput() {
        return input;
    }

    @Override
    public String getOutput() {
        return output;
    }

    @Override
    public SimpleCandidate toSimpleCandidate() {
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleCandidate that = (SimpleCandidate) o;

        if (input != null ? !input.equals(that.input) : that.input != null) return false;
        return !(output != null ? !output.equals(that.output) : that.output != null);

    }

    @Override
    public int hashCode() {
        int result = input != null ? input.hashCode() : 0;
        result = 31 * result + (output != null ? output.hashCode() : 0);
        return result;
    }
}
