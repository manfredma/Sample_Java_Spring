package operator.variables;

import domain.Inventor;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unchecked")
public class Main {
    public static void main(String[] args) {
        Inventor tesla = new Inventor("Nikola Tesla", "Serbian");
        StandardEvaluationContext context = new StandardEvaluationContext(tesla);
        ExpressionParser parser = new SpelExpressionParser();
        context.setVariable("newName", "Mike Tesla");
        parser.parseExpression("Name = #newName").getValue(context);
        System.out.println(tesla.getName()); // "Mike Tesla"


        // create an array of integers
        List<Integer> primes = new ArrayList<>();
        primes.addAll(Arrays.asList(2, 3, 5, 7, 11, 13, 17));
        // create parser and set variable 'primes' as the array of integers
        context.setVariable("primes", primes);

        // all prime numbers > 10 from the list (using selection ?{...})
        // evaluates to [11, 13, 17]
        @SuppressWarnings("unchecked")
        List<Integer> primesGreaterThanTen = (List<Integer>) parser.parseExpression(
                "#primes.?[#this>10]").getValue(context);
        System.out.println(primesGreaterThanTen);
    }
}
