package hr.k33zo.gamewebshop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/addItem").setViewName("addItem");
        registry.addViewController("/cart").setViewName("cart");
        registry.addViewController("/itemController/items").setViewName("home");
        registry.addViewController("/products").setViewName("products");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/editUser").setViewName("edituser");
    }

}
