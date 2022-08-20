package pl.coderslab.services.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.coderslab.services.UserLogger;
import pl.coderslab.utils.DBUtill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service("MySQLDBUserLogger")
//@Primary
@Profile("DB")
public class DBUserLoggerService implements UserLogger {

    private final DBUtill dbUtill;

    public final String LOG_QUERY="INSERT INTO BookAPI.log (record) VALUES (?);";


    @Autowired
    public DBUserLoggerService(DBUtill dbUtill) {
        this.dbUtill = dbUtill;
    }


    @Override
    public void log(String methodName) {
        try(Connection connect = dbUtill.connect()){

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String log = "<Actual Date and Time>:" + now.format(formatter) + " <action>: "+ methodName;

            PreparedStatement statement = connect.prepareStatement(LOG_QUERY);
            statement.setString(1, log);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
