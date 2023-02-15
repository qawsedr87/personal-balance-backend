package com.example.personalbalancebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class PersonalBalanceBackendApplication {

    public static void main(String[] args) {
//        Connection conn = null;
//
//        try {
//            String dbURL = "jdbc:sqlserver://localhost:1444;databaseName=Finance;encrypt=false";
//            String user = "sa";
//            String pass = "PersonalBalanceSystem";
//            conn = DriverManager.getConnection(dbURL, user, pass);
//            if (conn != null) {
//                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
//                System.out.println("Driver name: " + dm.getDriverName());
//                System.out.println("Driver version: " + dm.getDriverVersion());
//                System.out.println("Product name: " + dm.getDatabaseProductName());
//                System.out.println("Product version: " + dm.getDatabaseProductVersion());
//            }
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } finally {
//            try {
//                if (conn != null && !conn.isClosed()) {
//                    conn.close();
//                }
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//        }
        SpringApplication.run(PersonalBalanceBackendApplication.class, args);
    }

}
