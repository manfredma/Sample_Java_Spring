package compile;

import org.springframework.expression.Expression;
import org.springframework.expression.spel.SpelCompilerMode;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class Main {
    public static void main(String[] args) {
        SpelParserConfiguration config = new SpelParserConfiguration(SpelCompilerMode.IMMEDIATE, Main.class
                .getClassLoader());

        SpelExpressionParser parser = new SpelExpressionParser(config);
        //        SpelExpressionParser parser = new SpelExpressionParser();

        Expression expr = parser.parseExpression("payload");

        MyMessage message = new MyMessage();
        message.setPayload("xxxxxxx");

        Object payload = expr.getValue(message);
        System.out.println(payload);
        System.out.println(expr.getValue(new MyMessage2()));
    }
}


