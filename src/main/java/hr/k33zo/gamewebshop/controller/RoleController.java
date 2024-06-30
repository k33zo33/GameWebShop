package hr.k33zo.gamewebshop.controller;

import hr.k33zo.gamewebshop.service.UserDetailService;
import lombok.NoArgsConstructor;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@EnableWebSecurity
@RestController
@NoArgsConstructor
@RequestMapping("/roleController")
public class RoleController {

    UserDetailService userDetailService;

    @GetMapping("/getRole")
    public String getRole() {
        String role = userDetailService.getRole();
        return role;
    }
    @GetMapping("/username")
    public String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && !authentication.getName().equals("anonymousUser"))
        {
            return authentication.getName();
        }
        else {
            return "GUEST";
        }

    }

    @GetMapping("/userInfo")
    public Map<String, String> getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        String role = roles.contains("ADMIN") ? "ADMIN" : roles.contains("USER") ? "USER" : "GUEST";

        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("username", authentication.getName());
        userInfo.put("role", role);

        return userInfo;
    }

    @GetMapping("/isAuthenticated")
    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && !authentication.getName().equals("anonymousUser");
    }


}
