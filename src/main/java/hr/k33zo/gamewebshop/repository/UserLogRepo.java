package hr.k33zo.gamewebshop.repository;

import hr.k33zo.gamewebshop.model.UserLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLogRepo extends JpaRepository<UserLog, Long>{
}
