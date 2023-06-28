import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import print.print;
import static print.print.print;

public class CountdownSolver {//implements Runnable{

    private Thread t;

    print p = new print(7);

    int count = 0;

    int goal;

    Integer[] numbers;

    static volatile boolean done = false;

    HashMap<Integer, List<Expression>> solutions = new HashMap<>();

    ArrayList<Expression> queue = new ArrayList<Expression>();

    public CountdownSolver(int goal, Integer[] numbers) {
        this.goal = goal;
        this.numbers = numbers;

        p.log(6, Arrays.toString(numbers));
    }

    public void run(){
        solve(new ArrayList<Expression>(queue.subList(queue.size() - Math.min(1000, queue.size()), queue.size())), numbers.clone(), 0);
    }

    public void start(String name, List<Expression> start){
        if (t == null) {
            queue.addAll(start);
            //t = new Thread (this, name);
            //t.start ();
            run();
        }
    }

    private int solve(List<Expression> expressions, Integer[] numbers, int depth){
        if(depth % 10 == 0)
            p.log(2, depth + " " + queue.size(), solutions.size(), done);
        if(done)
            return -1;
        queue.subList(queue.size() - expressions.size(), queue.size()).clear();
        depth++;
        ArrayList<Expression> expressions2 = new ArrayList<Expression>();
        ArrayList<Integer> usedNumbers = new ArrayList<Integer>(numbers.length);
        for (int number : numbers) {
            if (usedNumbers.contains(number)){
                continue;
            }
            for (Expression expression : expressions){
                if (!expression.numbers.contains(number))
                    continue;
                usedNumbers.add(number);
                int leftValue = expression.rightMostValue;
                ArrayList<math> toBeAdded = new ArrayList<math>(6);
                toBeAdded.add(new plus(0));
                toBeAdded.add(new plus(2));
                toBeAdded.add(new minus(0));
                toBeAdded.add(new minus(2));
                toBeAdded.add(new divide(1));
                toBeAdded.add(new reverseDivide(1));
                toBeAdded.add(new multiply(1));
                for (math toAdd : toBeAdded) {
                    Expression newExpression = expression.addExpression(toAdd, leftValue, number);
                    count++;
                    if(newExpression != null){
                        if(Math.abs(newExpression.value - goal) < 11){
                            if(!solutions.containsKey(newExpression.value))
                                solutions.put(newExpression.value, new ArrayList<>());
                            solutions.get(newExpression.value).add(newExpression);
                            if(newExpression.value == goal){
                                p.log(7, depth + " count: " + count + " | " + newExpression.toString(), " value: " + newExpression.value);
                                done = true;
                                return goal;
                            }                      
                                
                        }
                        expressions2.add(newExpression);
                    }
                }
            }
        }

        if(expressions2.size() + queue.size() > 0){
            if(expressions2.size() == 0){
                return solve(new ArrayList<Expression>(queue.subList(queue.size() - Math.min(1000, queue.size()), queue.size())), numbers, depth); 
            }
            queue.addAll(expressions2);
            queue.sort((x, y) -> Math.abs(y.value - goal) - Math.abs(x.value - goal));
            p.log(2, queue.get(0));
            return solve(new ArrayList<Expression>(queue.subList(queue.size() - Math.min(1000, queue.size()), queue.size())), numbers, depth);
        }
        p.log(6, "End of Thread");
        return 0;
    }
}
