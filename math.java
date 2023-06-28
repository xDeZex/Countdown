import java.text.MessageFormat;

public abstract class math {

    protected int priority;

    public boolean equals(math x) {
        return false;
    }

    public abstract String toString();

    public String toString(int left, int right) {
        String pattern = "{0} {2} {1} ";
        return MessageFormat.format(pattern, left, right, this.toString());
    }
    public String toString(int right) {
        String pattern = "{1} {0} ";
        return MessageFormat.format(pattern, right, this.toString());
    }

    public abstract int calculate(int left, int right);

    public int operator() {
        return 1;
    }
}
