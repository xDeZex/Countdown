public class minus extends math {

    protected minus(int priority) {
        this.priority = priority;
    }

    public int calculate(int left, int right) {
        return left - right;
    }

    public String toString() {
        if(priority == 2)
            return "(minus)";
        return "minus";
    }

    public int operator() {
        return -1;
    }

}
