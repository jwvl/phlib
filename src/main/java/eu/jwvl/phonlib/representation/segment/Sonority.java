package eu.jwvl.phonlib.representation.segment;

/**
 * Created by janwillem on 02/04/16.
 */
    public enum Sonority {
        X("Unknown"),
        C("Consonant"),
        P("Stop"),
        F("Fricative"),
        N("Nasal"),
        L("Liquid"),
        J("Semivowel"),
        V("Vowel"); // Ordered by sonority!


        private final String fullName;

        /**
         * @param fullName
         */
        Sonority(String fullName) {
            this.fullName = fullName;
        }
}
