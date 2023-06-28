import java.util.ArrayList;
import static print.print.print;
public class ExpressionTest {

    public ExpressionTest() {
        ArrayList<Integer> temp = new ArrayList<Integer>();
        temp.add(3);
        temp.add(62);
        temp.add(123);
        temp.add(75);
        temp.add(2342);
        temp.add(10);
        temp.add(10);
        temp.add(100);
        temp.add(2);
        temp.add(46);
        temp.add(60);
        temp.add(62);
        temp.add(53);
        temp.add(25);
        temp.add(5);
        temp.add(75);

        Expression t = new Expression(temp);
        t = t.addExpression(new minus(2), 100, 2);
        t = t.addExpression(new multiply(1), 2, 46);
        t = t.addExpression(new minus(2), 46, 60);
        t = t.addExpression(new minus(0), 60, 62);
        t = t.addExpression(new plus(0), 62, 53);
        t = t.addExpression(new multiply(1), 53, 25);
        t = t.addExpression(new minus(2), 25, 5);
        t = t.addExpression(new plus(0), 5, 75);
        print("test");


        print(t);
        System.out.println(t.calculate());

        print(t);
        
        print("t: ", t.calculate());
        print(t);
    }
}
