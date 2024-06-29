package hr.k33zo.gamewebshop;


import hr.k33zo.gamewebshop.filter.IpLog;
import hr.k33zo.gamewebshop.model.Cart;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(new IpLog(), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/","/cart" , "cart/showCart","itemController/addItemToCart" ,"userRoleController/", "cart/add","/home", "/register", "/itemController/items", "/items", "/userRoleController/userInfo","/cart/remove").permitAll()
                        .requestMatchers("/addItem", "/products", "/edituser", "/transactionLog/logs", "/itemController/delete/", "/itemController/edit/", "/categoryController/delete/", "categoryController/add", "/categoryController/edit/").hasAuthority("ADMIN")

                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**")
                )

                .formLogin(withDefaults())

                .logout(logout -> logout
                        .logoutSuccessUrl("/").permitAll()
                );

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    @SessionScope
    public Cart cart() {
        return new Cart();
    }

    @Bean
    public FilterRegistrationBean<IpLog> loggingFilter(){
        FilterRegistrationBean<IpLog> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new IpLog());
        registrationBean.addUrlPatterns("/login/*");
        return registrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean<RequestContextListener> requestContextListener() {
        return new ServletListenerRegistrationBean<>(new RequestContextListener());
    }

}
