package pl.coderslab.services.map;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.coderslab.services.UserLogger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
//@Primary
public class UserLoggerService implements UserLogger {

    private List<String> logList=new ArrayList<>();

    public List<String> getLogList() {
        return logList;
    }

    public void setLogList(List<String> logList) {
        this.logList = logList;
    }

    @Override
    public void log(String methodName) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String log = "<Actual Date and Time>:" + now.format(formatter) + " <action>: "+ methodName;
        logList.add(log);
        System.out.println(log);
    }
}
