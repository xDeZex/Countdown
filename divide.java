public class divide extends math {

    protected divide(int priority) {
        this.priority = priority;
    }

    public int calculate(int left, int right) {
        if(right == 0 || left % right != 0)
            return Integer.MIN_VALUE;
        return left / right;
    }

    public String toString() {
        return "divide";
    }
}
