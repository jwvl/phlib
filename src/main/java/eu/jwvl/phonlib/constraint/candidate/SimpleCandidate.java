package eu.jwvl.phonlib.constraint.candidate;

/**
 * Created by janwillem on 03/10/15.
 */
public class SimpleCandidate extends AbstractCandidate<String,String> {


    public SimpleCandidate(String input, String output) {
        super(input,output);
    }


    @Override
    public SimpleCandidate toSimpleCandidate() {
        return this;
    }



    public String toString() {
        return getInputAsString()+" "+getOutputAsString();
    }
}
