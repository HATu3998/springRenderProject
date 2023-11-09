package com.example.demoSpringRender.controller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demoSpringRender.repo.CartItemRepository;
import com.example.demoSpringRender.repo.ProductRepository;
import com.example.demoSpringRender.repo.UserRepository;
import com.example.demoSpringRender.model.CartItem;
import com.example.demoSpringRender.model.Product;
import com.example.demoSpringRender.model.User;

import org.springframework.ui.Model;
import java.util.*;
import org.springframework.security.core.Authentication;
@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private UserRepository userRepository;
    
    
    @GetMapping("/")
    public String index(Model model ,Principal principal,  @PageableDefault(size = 8, sort = "id") Pageable pageable,
            @RequestParam(name = "searchTerm", required = false) String searchTerm) {

        Page<Product> topProductsPage;
        Page<Product> regularProductsPage;

        if (searchTerm != null && !searchTerm.isEmpty()) {
            // Nếu có giá trị tìm kiếm, thực hiện truy vấn theo điều kiện tìm kiếm
            topProductsPage = productRepository.findByProductTopAndNameContaining(true, searchTerm, pageable);
            regularProductsPage = productRepository.findByProductTopAndNameContaining(false, searchTerm, pageable);
        } else {
            // Nếu không có giá trị tìm kiếm, truy vấn tất cả sản phẩm
            topProductsPage = productRepository.findByProductTopTrue(pageable);
            regularProductsPage = productRepository.findByProductTopFalse(pageable);
        }

        model.addAttribute("topProductsPage", topProductsPage);
        model.addAttribute("regularProductsPage", regularProductsPage);

        if (principal != null) {
            model.addAttribute("usernamePrin", principal.getName());
        } else {
            model.addAttribute("usernamePrin", "");
        }
      

        return "temp";
    }
    
    @GetMapping("/users")
    public String showUserList(Model model) {
        List<User> users = userRepository.findAll(); // Thay thế findAll() bằng phương thức truy vấn người dùng của bạn
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/DangKy")
	public String dangKy() {
		return "DangKy";
	}
	
	@GetMapping("/showLoginPage")
	public String login() {
		return "login";
	}
 
	@GetMapping("/addProduct")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "addProduct"; // Return the HTML form for adding a product
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
	@GetMapping("/leaderCart")
	public String leaderCart(Model model) {
		List<CartItem> ca=cartItemRepository.findAll();
		   model.addAttribute("ca", ca);
		return "/leaderCart";
	}
	
	//chi tiet san pham
	 @GetMapping("/infoProduct/{productId}")
	    public String infoProduct(@PathVariable Long productId, Model model) {
	        // Tìm sản phẩm theo ID
	        Optional<Product> product = productRepository.findById(productId);

	        if (product.isPresent()) {
	            model.addAttribute("product", product.get());
	            return "product";
	        }

	      
	        return "redirect:product";  
	    }
	
	 @GetMapping("/product/{productId}")
	    public String showProduct(@PathVariable Long productId, Model model) {
	        // Tìm sản phẩm theo ID
	        Optional<Product> product = productRepository.findById(productId);

	        if (product.isPresent()) {
	            model.addAttribute("product", product.get());
	            return "proUpdate";
	        }

	        // Xử lý khi không tìm thấy sản phẩm
	        return "redirect:proUpdate";  
	    }
	 
	 //html update product từ leader
	 @GetMapping("/pro/{productId}")
	    public String upProduct(@PathVariable Long productId, Model model) {
	        // Tìm sản phẩm theo ID
	        Optional<Product> product = productRepository.findById(productId);

	        if (product.isPresent()) {
	            model.addAttribute("products", product.get());
	            return "update";
	        }

	        // Xử lý khi không tìm thấy sản phẩm
	        return "redirect:update";  
	    }
	 //add sp
	 @PostMapping("/addProduct")
	    public String addProduct(Model model,@ModelAttribute Product product) {
	        // Save the product to your database
	        productRepository.save(product);
	        
	        
	        List<Product> topProducts;
	        List<Product> regularProducts;

	         
	            // Nếu không có từ khóa tìm kiếm, hiển thị tất cả sản phẩm
	            topProducts = productRepository.findByProductTop(true);
	            regularProducts = productRepository.findAll();
	      
	        model.addAttribute("topProducts", topProducts);
	        model.addAttribute("regularProducts", regularProducts);
	        
	        return "redirect:leaders"; // Redirect to the product list page or any other appropriate page
	    }

	 //update controller
	  @PostMapping("updateSP/update/{id}")
	    public String updateProduct(@PathVariable Long id, @ModelAttribute Product updatedProduct) {
	        Product product = productRepository.findById(id).orElse(null);
	        if (product != null) {
	            product.setName(updatedProduct.getName());
	            product.setCategory(updatedProduct.getCategory());
	            product.setPriceFirst(updatedProduct.getPriceFirst());
	            product.setPrice(updatedProduct.getPrice());
	            product.setImageUrl(updatedProduct.getImageUrl());
	            product.setImageUrlSe(updatedProduct.getImageUrlSe());
	            product.setProductTop(updatedProduct.getProductTop());

	            productRepository.save(product);
	        }
	        return "redirect:/leaders";
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

	 @GetMapping("/removeProduct/{id}")
	    public String removProduct(Principal principal, Model model,@PathVariable Long id) {
	         
	        Product productItem= productRepository.findById(id).orElse(null);
	        BigDecimal totalPrice = BigDecimal.ZERO;
	        if (productItem == null) {
	            return "index";
	        }
	         productRepository.delete(productItem);
	        List<Product> regularProducts;

	            regularProducts = productRepository.findAll();
	        
	        model.addAttribute("regularProducts", regularProducts);
	        
	       model.addAttribute("usernamePrin", principal.getName());
	    
	    

	        return "leaders";
	    }
	 
}
