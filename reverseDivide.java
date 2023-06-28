public class reverseDivide extends math {

    protected reverseDivide(int priority) {
        this.priority = priority;
    }

    public int calculate(int left, int right) {
        if(left == 0 || right % left != 0)
            return Integer.MIN_VALUE;
        return right / left;
    }

    public String toString() {
        return "Rdivide";
    }
}
