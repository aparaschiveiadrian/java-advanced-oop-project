package org.adrianaparaschivei.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SchemaInit {
    public static void createTable(Connection connection, String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("Tabel creat sau deja existent.");
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la crearea tabelului: " + e.getMessage(), e);
        }
    }
}
