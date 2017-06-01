package driver.manager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

public class MainMysql {
    // MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
    // 避免中文乱码要指定useUnicode和characterEncoding
    // 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
    // 下面语句之前就要先创建javademo数据库
    static String url = "jdbc:mysql://localhost:3306/jdbc_test?user=root&password=635241&useUnicode=true&characterEncoding=UTF8";

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
        Connection connection = DriverManager.getConnection(url);
        for (int i = 0; i < 1000; i++) {
            System.out.println("index  " + i);
            CallableStatement callableStatement = connection.prepareCall("select sysdate()");
            callableStatement.executeQuery();
        }
        connection.close();
    }
}
