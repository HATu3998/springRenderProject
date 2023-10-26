package com.example.demoSpringRender.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoSpringRender.model.CartItem;
import com.example.demoSpringRender.model.Product;
import com.example.demoSpringRender.repo.CartItemRepository;
import com.example.demoSpringRender.repo.ProductRepository;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartItemRepository cartItemRepository;
    
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/{username}")
    public List<CartItem> getCartItemsByUsername(@PathVariable String username) {
        return cartItemRepository.findByUsername(username);
    }

    @GetMapping("/add")
    public String addToCart(Principal principal, 
    	    @RequestParam("productId") Long productId,
    	    @RequestParam("quantity") int quantity,
    	    @RequestParam("username") String username,
    	    @RequestParam("price") BigDecimal price) {

    	    Product product = productRepository.findById(productId).orElse(null);

    	    if (product == null) {
    	        return "/";
    	    }

    	    // Tạo một đối tượng CartItem và lưu thông tin vào đó
    	    CartItem cartItem = new CartItem(username, product, quantity, price);

    	    // Lưu CartItem vào cơ sở dữ liệu
    	    cartItem = cartItemRepository.save(cartItem);

    	    return "redirect:temp";
    	}

   
}