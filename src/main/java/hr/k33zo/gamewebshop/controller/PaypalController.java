package hr.k33zo.gamewebshop.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import hr.k33zo.gamewebshop.service.CartService;
import hr.k33zo.gamewebshop.service.PaypalService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/payment")
public class PaypalController {

//    @Value("${app.base-url}")
//    private String baseUrl;
//

//    @Value("${app.context-path}")
//    private String contextPath;

    @Autowired
    private HttpServletRequest request;

//    @Autowired
//    private ServletContext servletContext;
    private final PaypalService payPalService;

    private final CartService cartService;

    public PaypalController(PaypalService payPalService, CartService cartService) {
        this.payPalService = payPalService;
        this.cartService = cartService;
    }

    @GetMapping("/create")
    public RedirectView createPayment() {

        try{
//            String contextPath = servletContext.getContextPath();
//            String cancelUrl = contextPath + "/payment/cancel";
//            String successUrl = contextPath + "/payment/success";
//            String baseUrl = request.getRequestURL().toString();
//            String cancelUrl = baseUrl.replace("/create", "/cancel");
//            String successUrl = baseUrl.replace("/create", "/success");
            String baseUrl = getBaseUrl();
            String cancelUrl = baseUrl + "/payment/cancel";
            String successUrl = baseUrl + "/payment/success";
            Payment payment = payPalService.createPayment(
                    cartService.getTotalPriceOfItemsInCart(),
                    "USD",
                    "paypal",
                    "sale",
                    cartService.getProductNames(),
                    cancelUrl,
                    successUrl);

            for(Links links: payment.getLinks()){
                if(links.getRel().equals("approval_url")){
                    return new RedirectView(links.getHref());
                }
            }

        } catch (PayPalRESTException e) {

        }
        return new RedirectView("/payment/error");
    }

    @GetMapping("/success")
    public String PaymentSuccess(
            @RequestParam("paymentId") String paymentId,
            @RequestParam("PayerID") String payerId
    ){
        try {
            cartService.commitTransaction("paypal");
            Payment payment = payPalService.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if(payment.getState().equals("approved")){
                return "paymentSuccess";
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "redirect:/payment/error";
    }

    @GetMapping("/payment/cancel")
    public String paymentCancel(){
        return "paymentCancel";
    }


    @GetMapping("/payment/error")
    public String paymentError(){
        return "paymentError";
    }


    private String getBaseUrl() {
        String baseUrl = request.getRequestURL().toString();
        return baseUrl.replace(request.getRequestURI(), request.getContextPath());
    }
}
