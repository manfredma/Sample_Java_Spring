package driver.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.TimeZone;

public class MainOracle {

    public static void main(String[] args) throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@vm-address:1521:orcl", "manfred_customer", "manfred_customer");
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of("+7")));
        // 测试游标的问题。
//        for (int i = 0; i < 1000; i++) {
//            System.out.println("index  " + i);
//            CallableStatement callableStatement = connection.prepareCall("select * from customer");
//            callableStatement.executeQuery();
//        }
        PreparedStatement preparedStatement = connection.prepareStatement("insert into customer(id, active_date_time2) VALUES (seq_id.nextval, ?)");
//        preparedStatement.setLong(1, 12L);
        //preparedStatement.setDate(2, new Date(Instant.now().toEpochMilli()), c);
        preparedStatement.setObject(1, ZonedDateTime.now(ZoneId.of("+6")), JDBCType.TIME_WITH_TIMEZONE);
//        preparedStatement.setObject(2, ZonedDateTime.now(ZoneId.of("+6")));
        preparedStatement.execute();
        connection.close();
    }
}
