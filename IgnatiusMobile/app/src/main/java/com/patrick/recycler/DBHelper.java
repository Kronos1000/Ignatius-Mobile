package com.patrick.recycler;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {

    private static final String DB_URL = "jdbc:sqlserver://ignquizdb.database.windows.net:1433;database=IgnatiusDATA;";
    private static final String DB_USER = "DBAdmin";
    private static final String DB_PASS = "Password1234%^";

    public DBHelper(Context context) {
        // Initialize any required resources here
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

    public Boolean insertQuizData(String question, String subject, String option1, String option2, String option3, String answer) {
        try (Connection connection = getConnection()) {
            String query = "INSERT INTO Questions (question, subject, option1, option2, option3, answer) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, question);
                preparedStatement.setString(2, subject);
                preparedStatement.setString(3, option1);
                preparedStatement.setString(4, option2);
                preparedStatement.setString(5, option3);
                preparedStatement.setString(6, answer);

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteQuizData(String question) {
        try (Connection connection = getConnection()) {
            String query = "DELETE FROM Questions WHERE question = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, question);
                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<String> getTopics() {
        List<String> topics = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            String query = "SELECT DISTINCT subject FROM Questions";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    topics.add(resultSet.getString("subject"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the SQLException appropriately (e.g., log the error).

        }

        return topics;
    }


    public Cursor getQuizData(String selectedSubject) {
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM Questions WHERE subject = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, selectedSubject);
                ResultSet resultSet = preparedStatement.executeQuery();
                // Convert ResultSet to Cursor and return it
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public Cursor getData() {
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM Questions";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                // Convert ResultSet to Cursor and return it
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    // Methods openDatabase and closeDatabase are not applicable in Azure SQL.

    // The onCreate and onUpgrade methods are not needed in Azure SQL.

    // ... (other methods)
}
