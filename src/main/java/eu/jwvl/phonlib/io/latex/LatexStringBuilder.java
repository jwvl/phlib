package eu.jwvl.phonlib.io.latex;

/**
 * Created by janwillem on 24/03/16.
 * A decorator/warpper for a StringBuilder
 */
public class LatexStringBuilder {
    private StringBuilder builder;

    public LatexStringBuilder(String initialValue) {
        this.builder = new StringBuilder(initialValue);
    }

    public LatexStringBuilder() {
        this.builder = new StringBuilder();
    }

    public LatexStringBuilder append(String toAppend) {
        builder.append(toAppend);
        return this;
    }

    public LatexStringBuilder newLine() {
        builder.append("\n");
        return this;
    }

    public LatexStringBuilder command(String command, String content) {
        builder.append(LatexMethods.texCommand(command, content));
        return this;
    }

    public LatexStringBuilder command(String command) {
        builder.append(LatexMethods.texCommand(command));
        return this;
    }

    public LatexStringBuilder bracket(String toBracket) {
        builder.append(LatexMethods.bracket(toBracket));
        return this;
    }

    public LatexStringBuilder tab() {
        builder.append("\t");
        return this;
    }

    public void clear() {
        builder = new StringBuilder();
    }

    public String toString() {
        return builder.toString();
    }
}
