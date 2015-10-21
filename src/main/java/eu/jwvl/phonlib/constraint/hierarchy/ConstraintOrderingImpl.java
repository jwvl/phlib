package eu.jwvl.phonlib.constraint.hierarchy;

import com.google.common.collect.ImmutableList;

import java.util.Iterator;
import java.util.List;

/**
 * Created by janwillem on 18/10/15.
 */
public class ConstraintOrderingImpl implements ConstraintOrdering {
    private final List<Constraint> constraints;


    public ConstraintOrderingImpl(Constraint[] constraints) {
        this.constraints = ImmutableList.copyOf(constraints);
    }


    @Override
    public Constraint[] toArray() {
        return constraints.toArray(new Constraint[constraints.size()]);
    }

    @Override
    public Iterator<Constraint> iterator() {
        return constraints.iterator();
    }
}
