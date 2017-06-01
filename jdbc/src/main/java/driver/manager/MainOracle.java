package driver.manager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

public class MainOracle {

    public static void main(String[] args) throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@10.0.250.174:1521:starboss", "boss_customer", "boss_customer");
        for (int i = 0; i < 1000; i++) {
            System.out.println("index  " + i);
            CallableStatement callableStatement = connection.prepareCall("select * from customer");
            callableStatement.executeQuery();
        }

        connection.close();
    }
}
