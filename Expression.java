import java.util.ArrayList;

import print.print;
import static print.print.print;

public class Expression {

    print p = new print(7);
    public ArrayList<Integer> values = new ArrayList<Integer>();
    public ArrayList<math> operators = new ArrayList<math>();

    public int value = 0;
    ArrayList<Integer> numbers = new ArrayList<Integer>();
    ArrayList<Integer> numbersAll = new ArrayList<Integer>();
    public int rightMostValue;

    public Expression(ArrayList<Integer> numbers) {
        this.numbers = numbers;
        this.numbersAll = new ArrayList<Integer>(numbers);
    }

    public Expression(Expression expression) {
        this.value = expression.value;
        for (int number : expression.numbers) {
            this.numbers.add(number);
        }
        this.numbersAll = expression.numbersAll;
        
        for (int i = 0; i < expression.operators.size(); i++) {
            this.values.add(expression.values.get(i));
            this.operators.add(expression.operators.get(i));
        }
        if (expression.values.size() != 0){
            int value = expression.values.get(expression.values.size() - 1);
            this.values.add(value);
            this.rightMostValue = value;
        }
    }

    public String toString() {
        if(operators.size() > 0){
            StringBuilder result = new StringBuilder(operators.get(0).toString(values.get(0), values.get(1)));
            
            if(operators.size() > 1)
                for (int i = 1; i < operators.size(); i++) {
                    result.append(" ");
                    result.append(operators.get(i).toString(values.get(i + 1)));
                }
            return result.toString();
        }
        return "";
    }

    public Expression addExpression(math operator, int left, int right) {
        if(!numbers.contains(right))
            return null;
        int valuesSize = values.size();
        if (valuesSize > 0)
            if (values.get(valuesSize - 1) != left)
                return null;

        Expression newExpression = new Expression(this);
        if (newExpression.operators.size() == 0){
            newExpression.values.add(left);   
            newExpression.numbers.remove(newExpression.numbers.indexOf(left));
        }
        newExpression.values.add(right);
        newExpression.operators.add(operator);
        p.log(1, newExpression.operators.size(), newExpression.values.size());
        p.log(1, newExpression.operators, newExpression.values);

        newExpression.numbers.remove(newExpression.numbers.indexOf(right));
        newExpression.value = newExpression.calculate();

        newExpression.rightMostValue = right;

        return newExpression;
    }

    private int calc(int index, ArrayList<int[]> blocks, int[] calcValues){
        int lower = index;
        int upper = index + 1;
        int blockIndex = -1;
        if(blocks.size() == 0){
            blocks.add(new int[]{lower, upper});
            blockIndex = 0;
        }
        else{
            for (int i = 0; i < blocks.size(); i++) {
                int[] blocksI = blocks.get(i);
                if (blockIndex != -1)
                    break;
                if(i + 1 == blocks.size()){
                    if(blocksI[1] == lower){
                        blocksI[1] = upper;
                        blockIndex = i;
                    }
                    else{
                        blocks.add(new int[]{lower, upper});
                        blockIndex = i + 1;
                    }
                    break;
                }
                if(blocksI[1] < lower && blocks.get(i + 1)[0] > lower){
                    if (blocks.get(i + 1)[0] <= upper){
                        blocks.get(i + 1)[0] = lower;
                    }
                    else{
                        blocks.add(i + 1, new int[]{lower, upper});

                    }
                    blockIndex = i + 1;
                }
                if(blocksI[1] == lower){
                    blocksI[1] = upper;
                    if(blocks.get(i+1)[0] <= upper){
                        blocksI[1] = blocks.get(i+1)[1];
                        blocks.remove(i + 1);
                    }
                    blockIndex = i;
                }
            }
        }
        int value = operators.get(index).calculate(calcValues[lower], calcValues[upper]);
        if (blockIndex != -1){
            int [] block = blocks.get(blockIndex);
            int bottom = block[0];
            int top = block[1];
            for (int i = bottom; i <= top; i++) {
                calcValues[i] = value;
            }
        }
        return value;
    }

    public int calculate() {
        if(operators.size() == 1){
            return value = operators.get(0).calculate(values.get(0), values.get(1));
        }

        ArrayList<ArrayList<Integer>> queue = new ArrayList<ArrayList<Integer>>(3);

        for (int i = 0; i < 4; i++) {
            queue.add(new ArrayList<Integer>(numbers.size()));
        }

        for (int i = 0; i < operators.size(); i++) {
            queue.get(operators.get(i).priority).add(i);
        }

        int[] calcValues = new int[values.size()];

        ArrayList<int[]> blocks = new ArrayList<int[]>(numbers.size()/2);

        for (int i = 0; i < calcValues.length; i++) {
            calcValues[i] = values.get(i);
        }

        int value = 0;
        for (int i = queue.size() - 1; i >= 0; i--) {
            for (int index : queue.get(i)) {
                value = calc(index, blocks, calcValues);
                if(value == Integer.MIN_VALUE)
                    break;
            }
            if(value == Integer.MIN_VALUE)
                break;
        }

        return value;
    }
}
