package hr.k33zo.gamewebshop.listeners;

import hr.k33zo.gamewebshop.enums.Event;
import hr.k33zo.gamewebshop.service.UserLoggerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class LoginEventListener implements ApplicationListener<AuthenticationSuccessEvent> {
    private final UserLoggerService userLoggerService;

    public LoginEventListener(UserLoggerService userLoggerService) {
        this.userLoggerService = userLoggerService;
    }

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attr.getRequest();

        String ipAddress = request.getRemoteAddr();
        UserDetails userDetails = (UserDetails) event.getAuthentication().getPrincipal();

        System.out.println("Login success for user: " + userDetails.getUsername() + " from IP: " + ipAddress);

        userLoggerService.logUserEvent(userDetails.getUsername(), ipAddress, Event.LOGIN_SUCCESS);
    }
}
