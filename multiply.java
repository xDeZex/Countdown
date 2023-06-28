public class multiply extends math {

    protected multiply(int priority) {
        this.priority = priority;
    }

    public int calculate(int left, int right) {
        return left * right;
    }

    public String toString() {
        return "multiply";
    }

}
