package edu.miu.pm.onlineshopping.security;

import edu.miu.pm.onlineshopping.admin.model.Account;
import edu.miu.pm.onlineshopping.admin.model.Client;
import edu.miu.pm.onlineshopping.admin.model.EndUser;
import edu.miu.pm.onlineshopping.admin.model.Vendor;
import edu.miu.pm.onlineshopping.admin.service.AccountService;
import edu.miu.pm.onlineshopping.admin.service.ClientService;
import edu.miu.pm.onlineshopping.admin.service.EndUserService;
import edu.miu.pm.onlineshopping.admin.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class AuthenticationController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private VendorService vendorService;
    @Autowired
    private EndUserService endUserService;



    @GetMapping("/error/access-denied")
    public String deniedPage(){
        return "accessDenied";
    }

    @GetMapping("login")
    public String loginPage(@ModelAttribute("account") Account account, Model model) {
        return "login";
    }

    @GetMapping("login-error")
    public String loginPage(@ModelAttribute("account") Account account,
                            @RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {

        String errorMessge = null;
        if (error != null) {
            errorMessge = "Email or Password is incorrect !!";
        }
        if (logout != null) {
            errorMessge = "You have been successfully logged out !!";
        }
        model.addAttribute("errorMessge", errorMessge);
        return "login";
    }
    @GetMapping("login-success")
    public RedirectView logForward(Model model) {
        AccountDetails accountDetails = securityService.findLoggedInUser();
        EndUser endUser = endUserService.getEndUserByEmail(accountDetails.getEmail());
        Vendor vendor = vendorService.getVendorByEmail(accountDetails.getEmail());
        Client admin = clientService.getAdminByEmail(accountDetails.getEmail());
        if (endUser != null){
            return new RedirectView("home");
        }
        if (vendor != null){
            return new RedirectView("vendor");
        }
        if (admin != null){
            return new RedirectView("admin");
        }

        return new RedirectView("login");
    }

    @GetMapping("/denied")
    public String accessDenied(){
        return "accessDenied";
    }
}
