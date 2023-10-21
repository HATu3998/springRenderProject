package com.example.demoSpringRender.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demoSpringRender.repo.CartItemRepository;
import com.example.demoSpringRender.repo.ProductRepository;
import com.example.demoSpringRender.model.CartItem;
import com.example.demoSpringRender.model.Product;
 

import org.springframework.ui.Model;
import java.util.*;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
	@GetMapping("/")
	public String index(Model model, Principal principal, @RequestParam(value = "searchTerm", required = false) String searchTerm) {
		 List<Product> topProducts;
	        List<Product> regularProducts;

	        if (searchTerm != null && !searchTerm.isEmpty()) {
	            // Nếu có từ khóa tìm kiếm, thực hiện tìm kiếm sản phẩm
	            topProducts = productRepository.findByProductTopAndNameContaining(true, searchTerm);
	            regularProducts = productRepository.findByNameContaining(searchTerm);
	        } else {
	            // Nếu không có từ khóa tìm kiếm, hiển thị tất cả sản phẩm
	            topProducts = productRepository.findByProductTop(true);
	            regularProducts = productRepository.findAll();
	        }

	        model.addAttribute("topProducts", topProducts);
	        model.addAttribute("regularProducts", regularProducts);

	        if (principal != null) {
	            model.addAttribute("usernamePrin", principal.getName());
	        } else {
	            model.addAttribute("usernamePrin", "");
	        }
 
		return "temp";
	}
	

	
	 @GetMapping("/product/{productId}")
	    public String showProduct(@PathVariable Long productId, Model model) {
	        // Tìm sản phẩm theo ID
	        Optional<Product> product = productRepository.findById(productId);

	        if (product.isPresent()) {
	            model.addAttribute("product", product.get());
	            return "product";
	        }

	        // Xử lý khi không tìm thấy sản phẩm
	        return "redirect:/"; // Hoặc bạn có thể thêm trang lỗi 404 ở đây.
	    }
	 @GetMapping("/card/{usernamePrin}")
	 public String card(@PathVariable String usernamePrin, Model model) {
	     List<CartItem> ca = new ArrayList<>();
	     BigDecimal totalPrice = BigDecimal.ZERO; // Initialize total price to 0

	     if (usernamePrin != null) {
	         ca = cartItemRepository.findByUsername(usernamePrin);

	         // Calculate the total price by summing up individual item prices
	         for (CartItem item : ca) {
	             BigDecimal itemTotal = item.getTotal_price().multiply(BigDecimal.valueOf(item.getQuantity()));
	             totalPrice = totalPrice.add(itemTotal);
	         }
	     }
	       model.addAttribute("usernamePrin", usernamePrin);
	     model.addAttribute("ca", ca);
	     model.addAttribute("totalPrice", totalPrice); // Set the total price in the model
	     return "cart";
	 }
	 //giỏ hàng xóa sản phẩm
	 @GetMapping("/remove/{id}")
	    public String removeFromCart(Principal principal, Model model,@PathVariable Long id) {
	        CartItem cartItem = cartItemRepository.findById(id).orElse(null);
	        BigDecimal totalPrice = BigDecimal.ZERO;
	        if (cartItem == null) {
	            return "index";
	        }
	        List<CartItem> ca = ca = cartItemRepository.findByUsername(principal.getName());
	        cartItemRepository.delete(cartItem);
	        List<Product> regularProducts;

	            regularProducts = productRepository.findAll();
	        
	        model.addAttribute("regularProducts", regularProducts);
	        for (CartItem item : ca) {
	             BigDecimal itemTotal = item.getTotal_price().multiply(BigDecimal.valueOf(item.getQuantity()));
	             totalPrice = totalPrice.add(itemTotal);
	         }
	     
	       model.addAttribute("usernamePrin", principal.getName());
	     model.addAttribute("ca", ca);
	     model.addAttribute("totalPrice", totalPrice); // Set the total price in the model

	        return "cart";
	    }


	@GetMapping("/showLoginPage")
	public String login() {
		return "login";
	}
	
	@GetMapping("/leaders")
	public String showLeader(Model model, Principal principal, @RequestParam(value = "searchTerm", required = false) String searchTerm) {
		 List<Product> topProducts;
	        List<Product> regularProducts;

	        if (searchTerm != null && !searchTerm.isEmpty()) {
	            // Nếu có từ khóa tìm kiếm, thực hiện tìm kiếm sản phẩm
	            topProducts = productRepository.findByProductTopAndNameContaining(true, searchTerm);
	            regularProducts = productRepository.findByNameContaining(searchTerm);
	        } else {
	            // Nếu không có từ khóa tìm kiếm, hiển thị tất cả sản phẩm
	            topProducts = productRepository.findByProductTop(true);
	            regularProducts = productRepository.findAll();
	        }

	        model.addAttribute("topProducts", topProducts);
	        model.addAttribute("regularProducts", regularProducts);

	        if (principal != null) {
	            model.addAttribute("usernamePrin", principal.getName());
	        } else {
	            model.addAttribute("usernamePrin", "");
	        }

		return "leaders";
	}
	
	@GetMapping("/errorLogin")
	public String errorLogin() {
		return "/errorLogin";
	}
}
