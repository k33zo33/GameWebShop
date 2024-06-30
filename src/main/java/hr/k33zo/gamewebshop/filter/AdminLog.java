package hr.k33zo.gamewebshop.filter;

import hr.k33zo.gamewebshop.enums.Event;
import hr.k33zo.gamewebshop.service.UserLoggerService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@Order(2)
public class AdminLog implements Filter {

    private final UserLoggerService userLoggerService;


    public AdminLog(UserLoggerService userLoggerService) {
        this.userLoggerService = userLoggerService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        if ("POST".equalsIgnoreCase(request.getMethod()) &&
                (request.getServletPath().equals("/itemController/addItem") || request.getServletPath().startsWith("/categoryController/addCategory"))) {

            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username;
            username = principal.getClass().getSimpleName().equals("String") ? (String) principal : ((UserDetails) principal).getUsername();
            String ipAddress = request.getRemoteAddr();

            userLoggerService.logUserEvent(username, ipAddress, Event.ADMIN_ACTION);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
