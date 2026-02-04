
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.DBConnection;

public class Main {

    public static void main(String[] args) {
        System.out.println("Project initialized.");

        DBConnection.initializeDatabase();
    }
}
