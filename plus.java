public class plus extends math {

    protected plus(int priority) {
        this.priority = priority;
    }

    public int calculate(int left, int right) {
        return left + right;
    }

    public String toString() {
        if(priority == 2)
            return "(plus)";
        return "plus";
    }

    public int operator() {
        return 1;
    }
}
