package evaluation.context;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Simple simple = new Simple();

        simple.booleanList.add(true);

        StandardEvaluationContext simpleContext = new StandardEvaluationContext(simple);
        ExpressionParser parser = new SpelExpressionParser();
        // false is passed in here as a string. SpEL and the conversion service will
        // correctly recognize that it needs to be a Boolean and convert it
        parser.parseExpression("booleanList[0]").setValue(simpleContext, "false");

        // b will be false
        Boolean b = simple.booleanList.get(0);
        System.out.println(b);
    }
}

class Simple {
    public List<Boolean> booleanList = new ArrayList<Boolean>();
}

