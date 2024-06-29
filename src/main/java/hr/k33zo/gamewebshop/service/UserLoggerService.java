package hr.k33zo.gamewebshop.service;

import hr.k33zo.gamewebshop.enums.Event;
import hr.k33zo.gamewebshop.model.UserLog;
import hr.k33zo.gamewebshop.repository.UserLogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class UserLoggerService {

    @Autowired
    private UserLogRepo userLogRepo;

    @Autowired
    UserLoggerService(UserLogRepo userLogRepos) {
        this.userLogRepo = userLogRepo;
    }

    public void logUserEvent(String name, String ipAddress, Event event) {
        UserLog userLog = new UserLog();
        userLog.setName(name);
        userLog.setIpAddress(ipAddress);
        userLog.setLoginDate(Instant.now());
        userLog.setEvent(event);
        userLogRepo.save(userLog);
    }

    public List<UserLog> getAllLogs() {
        return userLogRepo.findAll();
    }
}
