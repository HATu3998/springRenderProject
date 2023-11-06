//package com.example.demoSpringRender.controller;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import com.example.demoSpringRender.model.*;
//import com.example.demoSpringRender.repo.AuthorityRepository;
//import com.example.demoSpringRender.repo.UserRepository;
//
//@Controller
//public class UserControllerAdd {
//	  @Autowired
//	    private UserRepository userRepository;
//	    @Autowired
//	    private AuthorityRepository authorityRepository;
//	    @Autowired
//	    private BCryptPasswordEncoder passwordEncoder;
//
//	    @RequestMapping("/registration")
//	    public String registrationPage() {
//	        return "registration"; // Thay thế bằng tên template hoặc đường dẫn tương ứng
//	    }
//
//	    @PostMapping("/userAdd")
//	    public String addUser(@RequestParam("username") String username,
//	                         @RequestParam("password") String password,
//	                         @RequestParam("confirmPassword") String confirmPassword, Model model) {
//	        // Kiểm tra xem mật khẩu và xác nhận mật khẩu có khớp nhau không
//	        if (!password.equals(confirmPassword)) {
//	            model.addAttribute("error", "Mật khẩu và xác nhận mật khẩu không khớp.");
//	            return "login"; // Thay thế bằng tên template hoặc đường dẫn tương ứng
//	        }
//
//	        // Kiểm tra xem tài khoản đã tồn tại chưa
//	        User existingUser = userRepository.findByUsername(username);
//	        if (existingUser != null) {
//	            model.addAttribute("error", "Tài khoản đã tồn tại.");
//	            return "login"; // Thay thế bằng tên template hoặc đường dẫn tương ứng
//	        }
//
//	        // Mã hóa mật khẩu trước khi lưu vào cơ sở dữ liệu
//	        String encodedPassword = passwordEncoder.encode(password);
//
//	        // Tạo một đối tượng User và Authority (nếu cần) và lưu vào cơ sở dữ liệu
//	        User user = new User(username, encodedPassword, 1); // enabled = 1 cho tài khoản được kích hoạt
//	        Authority authority = new Authority(user.getUsername(), "ROLE_EMPLOYEE"); // ROLE_USER là một quyền mặc định
//
//	        userRepository.save(user);
//	        authorityRepository.save(authority);
//
//	        model.addAttribute("success", "Đăng ký thành công.");
//	        return "login"; // Thay thế bằng tên template hoặc đường dẫn tương ứng
//	    }
//}
