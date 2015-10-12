package eu.jwvl.phonlib.representation.segment;

import eu.jwvl.phonlib.symbol.types.Boundary;

/**
 * Created by janwillem on 04/10/15.
 */
public class BoundaryPhonologicalUnit implements PhonologicalUnit {

    private final Boundary boundary;

    public BoundaryPhonologicalUnit(Boundary boundary) {
        this.boundary = boundary;
    }

    @Override
    public String getSymbol() {
        return boundary.toString();
    }
}
