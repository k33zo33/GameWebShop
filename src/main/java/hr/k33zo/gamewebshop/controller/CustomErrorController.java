package hr.k33zo.gamewebshop.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        // Get the error status
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        // Log the error or handle accordingly
        System.out.println("Error with status code: " + statusCode);
        // Return the appropriate view
        return "error";  // Ensure you have error.html template in src/main/resources/templates/
    }


//    @Override
//    public String getErrorPath() {
//        return "/error";
//    }
}
