package jm.task.core.jdbc.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

public class Util {
    private static Connection connection = null;
    private static Util instance = null;
    private Util() {
        try {
            if (connection == null || connection.isClosed()){
                Properties properties = getProps();
                connection = DriverManager.getConnection(properties.getProperty("db.url"),
                        properties.getProperty("db.username"), properties.getProperty("db.password"));
            }
        } catch (IOException | SQLException e){
            e.printStackTrace();
        }
    }
    public static Util getInstance(){
        if (instance == null){
            instance = new Util();
        }
        return instance;
    }
    public Connection getConnection(){
        return connection;
    }

    private static Properties getProps() throws IOException {
        Properties properties = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get(Objects.requireNonNull(Util.class.getResource("/database.properties")).toURI()))) {
            properties.load(in);
            return properties;
        } catch (IOException | URISyntaxException u){
            throw new IOException("Base config not found", u);
        }


    }


}
