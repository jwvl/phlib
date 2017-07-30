package eu.jwvl.phonlib.feature.encode;

import java.util.Arrays;
import java.util.Objects;

/**
 * Created by janwillem on 15/04/2017.
 */
public class WordFormAndPhones {
    public final byte[] phones;
    public final String wordForm;

    public WordFormAndPhones(byte[] phones, String wordForm) {
        this.phones = phones;
        this.wordForm = wordForm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordFormAndPhones that = (WordFormAndPhones) o;
        return Arrays.equals(phones, that.phones) &&
                Objects.equals(wordForm, that.wordForm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phones, wordForm);
    }
}
