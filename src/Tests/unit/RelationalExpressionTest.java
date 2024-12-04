package Tests.unit;

import Helpers.RemoveRedundantEnters;
import Lexical_analyzer.TokenType;
import Nodes.Enums.Type;
import Nodes.expression.AdditiveExpression;
import Nodes.expression.RelationalExpression;
import Nodes.jasmine.CodeGenerator;
import Tests.factory.AdditiveExpressionFactory;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RelationalExpressionTest {
    @Test
    public void twoIntegers() {
        final CodeGenerator generator = new CodeGenerator();

        AdditiveExpression add1 = AdditiveExpressionFactory.buildOneIntegerAddition(5);
        AdditiveExpression add2 = AdditiveExpressionFactory.buildOneIntegerAddition(8);

        ArrayList<AdditiveExpression> additiveExpressions = new ArrayList<>();
        additiveExpressions.add(add1);
        additiveExpressions.add(add2);

        ArrayList<TokenType> operations = new ArrayList<>();
        operations.add(TokenType.LESS_THAN);

        RelationalExpression rel = new RelationalExpression(additiveExpressions, operations, Type.BOOLEAN);
        rel.generateCode(generator);

        // Test
        String jasminCode = generator.getProgramText();
        jasminCode = RemoveRedundantEnters.remove(jasminCode);
        System.out.println(jasminCode); // Output generated Jasmin code


//        Dynamically save the Jasmin code to a.j file
        try (FileWriter writer = new FileWriter("C:\\Users\\HUAWEI\\Downloads\\jasmin-2.4\\MyProgram.j")) {
            writer.write(jasminCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    /*
        // Run the Jasmin bytecode and capture the output
        RunJasmin.runJasminProgram("MyProgram.j");*/
    }
}
