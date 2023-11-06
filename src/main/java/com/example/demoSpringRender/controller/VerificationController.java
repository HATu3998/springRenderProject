package com.example.demoSpringRender.controller;
import com.example.demoSpringRender.model.*;
import com.example.demoSpringRender.repo.AuthorityRepository;
import com.example.demoSpringRender.repo.DiscountCodeRepository;
import com.example.demoSpringRender.repo.ProductRepository;
import com.example.demoSpringRender.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
 
import java.util.Random;


@Controller
public class VerificationController {
	  private final UserRepository userRepository;
	 // private final PasswordEncoder passwordEncoder;
	    private final AuthorityRepository authorityRepository;

	    @Autowired
	    public VerificationController(UserRepository userRepository,
	    	//	PasswordEncoder passwordEncoder ,
	    		AuthorityRepository authorRepo) {
	        this.userRepository = userRepository;
	     //   this.passwordEncoder = passwordEncoder;
	        this.authorityRepository=authorRepo;
	    } 
 
	 
    @Autowired
    private EmailService emailService;

    private String verificationCode = "";

    @GetMapping("/send-verification-code")
    public String sendVerificationCodePage() {
        return "send-verification-code";
    }
    
    

    
    
    @PostMapping("/userAdd")
    public String verifyCode( 
                            @RequestParam("username") String username, 
                            @RequestParam("password") String password, 
                            @RequestParam("confirmPassword") String confirmPassword, 
                            Model model) {
      
            if (password.equals(confirmPassword)) {
            	User user = new User(username,"{noop}"+password,1);
            //	User user = new User(username,passwordEncoder().encode(password),1);
              
            	userRepository.save(user);

                // Thêm quyền "ROLE_EMPLOYEE" cho người dùng mới đăng ký
                Authority authority = new Authority(user.getUsername(),"ROLE_EMPLOYEE");
                
                authorityRepository.save(authority);
                model.addAttribute("error", "đăng ký thành công");
                System.out.print("đăng kí thành công");
                return "login";
            } else {
            	  System.out.print("đăng kí ko thành công");
                model.addAttribute("error", "Xác nhận mật khẩu không khớp");
                return "errorLogin";
            }
       
    }


    @PostMapping("/send-verification-code")
    public String sendVerificationCode(@RequestParam("email") String email) {
        // Generate a random verification code
        verificationCode = generateVerificationCode();
        emailService.sendVerificationCode(email, verificationCode);
        return "verify-code";
    }

    @GetMapping("/verify-code")
    public String verifyCodePage() {
        return "verify-code";
    }

    @PostMapping("/verify-code")
    public String verifyCode(@RequestParam("code") String code, Model model) {
        if (code.equals(verificationCode)) {
            return "success";
        } else {
            model.addAttribute("error", "Invalid verification code");
            return "verify-code";
        }
    }

    private String generateVerificationCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

}
