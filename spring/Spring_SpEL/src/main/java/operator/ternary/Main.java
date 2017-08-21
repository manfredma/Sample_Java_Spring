package operator.ternary;

import domain.Inventor;
import domain.Society;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class Main {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Inventor tesla = new Inventor("Nikola Tesla", "Serbian");
        // Inventions Array
        StandardEvaluationContext teslaContext = new StandardEvaluationContext(tesla);
        ExpressionParser parser = new SpelExpressionParser();

        Society ieee = new Society();
        ieee.getMembers().add(tesla);

        // Members List
        StandardEvaluationContext societyContext = new StandardEvaluationContext(ieee);
        parser.parseExpression("Name").setValue(societyContext, "IEEE");
        societyContext.setVariable("queryName", "Nikola Tesla");

        String expression = "isMember(#queryName)? #queryName + ' is a member of the ' " +
                "+ Name + ' Society' : #queryName + ' is not a member of the ' + Name + ' Society'";

        String queryResultString = parser.parseExpression(expression)
                .getValue(societyContext, String.class);
        System.out.println(queryResultString);
        // queryResultString = "Nikola Tesla is a member of the IEEE Society"
    }
}
