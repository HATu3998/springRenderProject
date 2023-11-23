package com.example.demoSpringRender.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demoSpringRender.model.DiscountCode;
import com.example.demoSpringRender.repo.DiscountCodeRepository;

 

@org.springframework.stereotype.Controller
public class DiscountController {

	@Autowired
    private DiscountCodeRepository discountCodeRepository;
    @GetMapping("/discounts")
    public String showDiscounts(Model model) {
        List<DiscountCode> discountCodes = discountCodeRepository.findAll();
        model.addAttribute("discountCodes", discountCodes);
        return "discounts"; // Tên view template (discounts.html)
    }

    // Các phương thức xử lý thêm, sửa, xóa mã giảm giá có thể được thêm ở đây
}