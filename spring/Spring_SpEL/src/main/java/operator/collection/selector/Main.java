package operator.collection.selector;

import domain.Inventor;
import domain.Society;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Inventor tesla = new Inventor("Nikola Tesla", "Serbian");
        Inventor tesla2 = new Inventor("Mike Tesla", "Serbian");
        Inventor tesla3 = new Inventor("Mike Tesla", "USA");
        // Inventions Array
        Society ieee = new Society();
        ieee.getMembers().add(tesla);
        ieee.getMembers().add(tesla2);
        ieee.getMembers().add(tesla3);
        ExpressionParser parser = new SpelExpressionParser();
        // Members List
        StandardEvaluationContext societyContext = new StandardEvaluationContext(ieee);

        List<Inventor> list = (List<Inventor>) parser.parseExpression(
                "Members.?[Nationality == 'Serbian']").getValue(societyContext);
        System.out.println(list);

        System.out.println(parser.parseExpression(
                "Members.^[Nationality == 'Serbian']").getValue(societyContext));
        System.out.println(parser.parseExpression(
                "Members.$[Nationality == 'Serbian']").getValue(societyContext));
    }
}
