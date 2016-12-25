package eu.jwvl.phonlib.constraint.candidate;

import eu.jwvl.phonlib.representation.structure.Form;

/**
 * Created by janwillem on 24/03/16.
 */
public abstract class AbstractFormCandidate<F extends Form, G extends Form> extends AbstractCandidate<F,G> {

    public AbstractFormCandidate(F input, G output) {
        super(input, output);
    }
}
