package eu.jwvl.phonlib.constraint.hierarchy;

/**
 * Created by janwillem on 12/10/15.
 */
public interface ConstraintOrdering extends Iterable<Constraint> {

    Constraint[] toArray();
}
