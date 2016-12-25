package eu.jwvl.phonlib.rule.rewrite;

import eu.jwvl.phonlib.representation.structure.Form;

/**
 * Created by janwillem on 02/10/15.
 */
public interface RewriteRule<F extends Form> {
    public String getName();
    public F rewrite(F original);
    public boolean isIterative();
}
