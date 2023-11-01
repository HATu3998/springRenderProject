package com.example.demoSpringRender.controller;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

 
@org.springframework.stereotype.Controller
public class EmailVerificationController {

    @GetMapping("/email-form")
    public String showEmailForm() {
        return "email-form";
    }

    @PostMapping("/send-verification-code")
    public String sendVerificationCode(@RequestParam("email") String email, Model model) {
    	  String to = "abcd@gmail.com";

          // Email ID cua nguoi gui can duoc de cap den
          String from = "web@gmail.com";

          // Gia su ban dang gui email tu localhost
          String host = "localhost";

          // Lay cac system properties
          Properties properties = System.getProperties();

          // Thiet lap mail server
          properties.setProperty("mail.smtp.host", host);

          // Lay doi tuong Session mac dinh.
          Session session = Session.getDefaultInstance(properties);

          try{
             // Tao mot doi tuong MimeMessage mac dinh.
             MimeMessage message = new MimeMessage(session);

             // Set From: header field.
             message.setFrom(new InternetAddress(from));

             // Set To: header field.
             message.addRecipient(Message.RecipientType.TO,
                                      new InternetAddress(to));

             // Set Subject: header field
             message.setSubject("Day la dong Subject!");

             // Bay gio thiet lap message thuc su
             message.setText("Day la message thuc su");

             // Send message
             Transport.send(message);
             System.out.println("Gui message thanh cong....");
          }catch (MessagingException mex) {
             mex.printStackTrace();
          }
    	
    	
    	
        // Tạo mã xác nhận ngẫu nhiên gồm 6 chữ số
        String verificationCode = generateRandomCode();

        // Gửi mã xác nhận đến email của người dùng
        sendEmail(email, verificationCode);

        // Lưu mã xác nhận vào session để kiểm tra sau này
        model.addAttribute("verificationCode", verificationCode);

        // Chuyển hướng đến trang nhập mã xác nhận
        return "redirect:/verify-code?email=" + email;
    }

    @GetMapping("/verify-code")
    public String showVerificationCodeForm(@RequestParam("email") String email) {
        return "verification-code-form";
    }

    @PostMapping("/check-verification-code")
    public String checkVerificationCode(@RequestParam("email") String email, @RequestParam("code") String code, Model model) {
        // Lấy mã xác nhận từ session
        String verificationCode = (String) model.getAttribute("verificationCode");

        // Kiểm tra mã xác nhận có hợp lệ không
        if (code.equals(verificationCode)) {
            // Nếu hợp lệ, chuyển hướng đến trang thành công
            return "redirect:/success";
        } else {
            // Nếu không hợp lệ, chuyển hướng về trang nhập mã xác nhận với thông báo lỗi
            return "redirect:/verify-code?email=" + email + "&error=true";
        }
    }

    @GetMapping("/success")
    public String showSuccessPage() {
        return "success";
    }

    private String generateRandomCode() {
        // Tạo mã xác nhận ngẫu nhiên gồm 6 chữ số
        Random random = new Random();
        int code = 100000 + random.nextInt(900000); // Số ngẫu nhiên trong khoảng 100000-999999
        return String.valueOf(code);
    }

   
    
}
