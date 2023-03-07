package com.getir.ebooks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//@EnableJpaRepositories
@SpringBootApplication
public class EbooksApplication {


//    // JDBC driver name and database URL
//    static final String JDBC_DRIVER = "org.h2.Driver";
//    static final String DB_URL = "jdbc:h2:C:/Users/gg/data";
//
//    //  Database credentials
//    static final String USER = "sa";
//    static final String PASS = "123";
    public static void main(String[] args) {
        SpringApplication.run(EbooksApplication.class, args);

//        Connection conn = null;
//        Statement stmt = null;
//
//        try {
//            //STEP 2: Register JDBC driver
//            Class.forName(JDBC_DRIVER);
//
//            //STEP 3: Open a connection
//            System.out.println("Connecting to a selected database...");
//            conn = DriverManager.getConnection(DB_URL, USER, PASS);
//            System.out.println("Connected database successfully...");
//
//            //STEP 4: Execute a query
//            System.out.println("Inserting records into the table...");
//            stmt = conn.createStatement();
//
//            String sql = "INSERT INTO user " +
//                    "VALUES (1, 'sema', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 'BCRYPT', 'database')";
//
//            stmt.executeUpdate(sql);
//
//            System.out.println("Inserted records into the table...");
//
//        } catch (SQLException se) {
//            //Handle errors for JDBC
//            se.printStackTrace();
//        } catch (Exception e) {
//            //Handle errors for Class.forName
//            e.printStackTrace();
//        } finally {
//            //finally block used to close resources
//            try {
//                if (stmt!=null)
//                    conn.close();
//            } catch (SQLException se) {
//            } // do nothing
//            try {
//                if (conn!=null)
//                    conn.close();
//            } catch (SQLException se) {
//                se.printStackTrace();
//            } //end finally try
//        } //end try
//
//        System.out.println("Goodbye!");
    }

}
