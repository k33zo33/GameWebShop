package hr.k33zo.gamewebshop.filter;

import hr.k33zo.gamewebshop.service.UserLoggerService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class IpLog implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(IpLog.class);

    private UserLoggerService userLogService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        if ("POST".equalsIgnoreCase(httpRequest.getMethod()) && "/login".equals(httpRequest.getServletPath())) {
            String ipAddress = httpRequest.getHeader("X-Forwarded-For");
            if (ipAddress == null || ipAddress.isEmpty()) {
                ipAddress = request.getRemoteAddr();
            }

            logger.info("Login attempt from IP: " + ipAddress);
        }

        chain.doFilter(request, response);
    }

}
