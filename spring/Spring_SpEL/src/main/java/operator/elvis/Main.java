package operator.elvis;

import domain.Inventor;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class Main {
    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();

        Inventor tesla = new Inventor("Nikola Tesla", "Serbian");
        StandardEvaluationContext context = new StandardEvaluationContext(tesla);

        String name = parser.parseExpression("Name?:'Elvis Presley'").getValue(context, String.class);

        System.out.println(name); // Nikola Tesla

        tesla.setName(null);

        name = parser.parseExpression("Name?:'Elvis Presley'").getValue(context, String.class);

        System.out.println(name); // Elvis Presley
    }
}
