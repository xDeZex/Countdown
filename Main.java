import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static print.print.print;
import print.print;

public class Main {

    public static void main(String[] args) {
        //new ExpressionTest();
        //new CountdownSolver(1000, new Integer[]{10, 10, 2});
        //new CountdownSolver(2, new Integer[]{22, 5, 6});
        print p = new print(7);
        Random rand = new Random(1);
        int goal = rand.nextInt(99999999);
        Integer[] numbers = new Integer[rand.nextInt(20, 21)];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = rand.nextInt(99);
        }

        //goal = 14348907;
        // numbers = new Integer[]{1, 2, 3};

        p.log(7, "Goal:", goal, "Amount of numbers:", numbers.length);
        List<Expression> expressions = StartSolve(numbers);

        List<CountdownSolver> solvers = new ArrayList<CountdownSolver>();

        for (int i = 0; i < Runtime.getRuntime().availableProcessors() - 15; i++) {
            solvers.add(new CountdownSolver(goal, numbers));
        }
        int i = 0;
        int previous = 0;
        p.log(6, expressions.size(), " whole");
        p.log(2, expressions);
        int size = 0;
        int step = expressions.size() / solvers.size();
        if (step < 1)
            step = 1;
        p.log(6, step);
        for (CountdownSolver solver : solvers) {
            
            int next = previous + step;
            if (next >= expressions.size())
                next = expressions.size() - 1;
            if (i == solvers.size() - 1)
                next = expressions.size();
            p.log(6, "part size", expressions.subList(previous, next).size());
            solver.start(Integer.toString(previous), expressions.subList(previous, next));
            previous = next;
            i++;
            size = next;
        }
        p.log(6, "size:", size);
    }

    private static List<Expression> StartSolve(Integer[] numbers){
        List<Expression> expressions = new ArrayList<Expression>();
        ArrayList<Integer> usedNumbersOuter = new ArrayList<Integer>(numbers.length);
        for (int i = 0; i < numbers.length; i++) {
            ArrayList<Integer> usedNumbersInner = new ArrayList<Integer>(numbers.length);
            if (usedNumbersOuter.contains(numbers[i]))
                continue;
            int numberI = numbers[i];
            usedNumbersOuter.add(numbers[i]);
            for (int j = 0; j < numbers.length; j++) {
                if(i != j) {
                    if (usedNumbersInner.contains(numbers[j]))
                        continue;
                    int numberJ = numbers[j];
                    usedNumbersInner.add(numberJ);
                    expressions.add(new Expression(new ArrayList<Integer>(Arrays.asList(numbers))).addExpression(new plus(0), numberI, numberJ));
                    expressions.add(new Expression(new ArrayList<Integer>(Arrays.asList(numbers))).addExpression(new plus(2), numberI, numberJ));
                    expressions.add(new Expression(new ArrayList<Integer>(Arrays.asList(numbers))).addExpression(new minus(0), numberI, numberJ));
                    expressions.add(new Expression(new ArrayList<Integer>(Arrays.asList(numbers))).addExpression(new minus(2), numberI, numberJ));
                    expressions.add(new Expression(new ArrayList<Integer>(Arrays.asList(numbers))).addExpression(new divide(1), numberI, numberJ));
                    expressions.add(new Expression(new ArrayList<Integer>(Arrays.asList(numbers))).addExpression(new reverseDivide(1), numberI, numberJ));
                    expressions.add(new Expression(new ArrayList<Integer>(Arrays.asList(numbers))).addExpression(new multiply(1), numberI, numberJ)); 
                }
            }
        }
        return expressions;
    }
}
