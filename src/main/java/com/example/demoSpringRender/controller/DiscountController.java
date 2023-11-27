package com.example.demoSpringRender.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demoSpringRender.model.CartItem;
import com.example.demoSpringRender.model.DiscountCode;
import com.example.demoSpringRender.repo.CartItemRepository;
import com.example.demoSpringRender.repo.DiscountCodeRepository;

 

@org.springframework.stereotype.Controller
public class DiscountController {
	@Autowired
    private CartItemRepository cartItemRepository;
	@Autowired
    private DiscountCodeRepository discountCodeRepository;
    @GetMapping("/discounts")
    public String showDiscounts(Model model) {
        List<DiscountCode> discountCodes = discountCodeRepository.findAll();
        model.addAttribute("discountCodes", discountCodes);
        return "discounts"; // Tên view template (discounts.html)
    }
    @GetMapping("/leaderDiscount")
    public String showLeaderDiscount(Model model) {
        List<DiscountCode> discountCodes = discountCodeRepository.findAll();
        model.addAttribute("discountCodes", discountCodes);
        return "leaderDiscount"; // Tên view template (discounts.html)
    }

    
    @GetMapping("/apply-discount")
    public String applyDiscount(@RequestParam("discountCode") String discountCode, Model model, @ModelAttribute("usernamePrin") String usernamePrin) {
        // Kiểm tra mã giảm giá có tồn tại trong cơ sở dữ liệu hay không
        DiscountCode code = discountCodeRepository.findByCode(discountCode);

        if (code != null) {
            // Lấy giỏ hàng của người dùng từ cơ sở dữ liệu
            List<CartItem> cartItems = cartItemRepository.findByUsername(usernamePrin);

            // Tính toán tổng giá trước khi áp dụng mã giảm giá
            BigDecimal totalPriceBeforeDiscount = BigDecimal.ZERO;
            for (CartItem item : cartItems) {
                totalPriceBeforeDiscount = totalPriceBeforeDiscount.add(item.getTotal_price());
            }

            // Tính toán giảm giá dựa trên tổng giá trị giỏ hàng
            BigDecimal discountAmount = totalPriceBeforeDiscount.multiply(code.getDiscountPercent().divide(BigDecimal.valueOf(100)));
            BigDecimal totalPriceAfterDiscount = totalPriceBeforeDiscount.subtract(discountAmount);

            // Cập nhật lại giá trị tổng giá sau khi giảm giá
            model.addAttribute("ca", cartItems);
            model.addAttribute("totalPrice", totalPriceAfterDiscount);
            model.addAttribute("discountAmount", discountAmount);
            model.addAttribute("discountInfo", code);

            // Có thể lưu thông tin giảm giá vào cơ sở dữ liệu tại đây nếu cần

            return "cart"; // Trả về trang hiển thị thông tin giảm giá đã áp dụng
        } else {
            // Nếu mã giảm giá không tồn tại, xử lý thông báo lỗi
            model.addAttribute("errorMessage", "Mã giảm giá không hợp lệ");
            return "cart"; // Trả về trang hiển thị thông báo lỗi
        }
    }

    }
 