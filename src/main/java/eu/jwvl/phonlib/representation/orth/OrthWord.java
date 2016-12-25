package eu.jwvl.phonlib.representation.orth;

import eu.jwvl.phonlib.representation.structure.Form;

/**
 * Created by janwillem on 02/09/16.
 */
public class OrthWord {
    private final String orthography;
    private final Form form;

    public OrthWord(String orthography, Form form) {
        this.orthography = orthography;
        this.form = form;
    }

    public String getOrthography() {
        return orthography;
    }

    public Form getForm() {
        return form;
    }
}
