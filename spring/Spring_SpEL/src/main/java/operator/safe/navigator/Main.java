package operator.safe.navigator;

import domain.Inventor;
import domain.PlaceOfBirth;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class Main {
    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();

        Inventor tesla = new Inventor("Nikola Tesla", "Serbian");
        tesla.setPlaceOfBirth(new PlaceOfBirth("Smiljan"));

        StandardEvaluationContext context = new StandardEvaluationContext(tesla);

        String city = parser.parseExpression("PlaceOfBirth?.City").getValue(context, String.class);
        System.out.println(city); // Smiljan

        tesla.setPlaceOfBirth(null);

        city = parser.parseExpression("PlaceOfBirth?.City").getValue(context, String.class);

        System.out.println(city); // null - does not throw NullPointerException!!!
    }
}
