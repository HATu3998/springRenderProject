package com.example.demoSpringRender.controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demoSpringRender.repo.*;

import com.example.demoSpringRender.model.*;
@Component
public class DataLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final DiscountCodeRepository discountCodeRepository;
    private final AuthorityRepository authorRepo;

    @Autowired
    public DataLoader(UserRepository userRepository, ProductRepository productRepository, DiscountCodeRepository discountCodeRepository, AuthorityRepository authorRepo) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.discountCodeRepository = discountCodeRepository;
        this.authorRepo=authorRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        // Thêm dữ liệu vào cơ sở dữ liệu ở đây, ví dụ:
//        User user1 = new User("join", "{noop}test123", 1);
//        User user2 = new User("mary", "{noop}test123", 1);
//        userRepository.saveAll(Arrays.asList(user1, user2));
//        
//        Authority au1=new Authority(user1.getUsername(), "ROLE_EMPLOYEE");
//        Authority au2=new Authority(user2.getUsername(), "ROLE_ADMIN");
//        authorRepo.saveAll(Arrays.asList(au1,au2));
//        
//        Product product1 = new Product("Quần Kaki Nam Cạp Chun Dáng Rộng Phối Túi Thời Trang Zenkocs3 MEN QUAN 049", "quan",new BigDecimal("300"), new BigDecimal("150"), "https://imgtr.ee/images/2023/10/05/ca41a824f3b912af190044778cf67639.jpeg","", false);
//        Product product2 = new Product("Áo polo nam nữ local brand unisex Fearow Signature", "ao",  new BigDecimal("100"), new BigDecimal("50"), "https://imgtr.ee/images/2023/10/05/c02e496e2752f868fce0a3cdb3c85ec7.jpeg","", false);
//        Product product3 = new Product("Tee basic ss1 CREWZ áo thun tay lỡ unisex Local Brand - AO_THUN_DVR", "ao", new BigDecimal("200"), new BigDecimal("100"), "https://imgtr.ee/images/2023/10/08/fee2ead15893a9a9df67fe39fcb087bb.jpeg", "https://imgtr.ee/images/2023/10/08/a3f4038aea579c4f98753e37bca023be.jpeg", true);
//        productRepository.saveAll(Arrays.asList(product1, product2, product3));
//
//        DiscountCode discountCode1 = new DiscountCode("DISCOUNT10", new BigDecimal("10.00"), Date.valueOf("2023-01-01"), Date.valueOf("2023-12-31"));
//        DiscountCode discountCode2 = new DiscountCode("DISCOUNT20", new BigDecimal("20.00"), Date.valueOf("2023-01-01"), Date.valueOf("2023-12-31"));
//
//        discountCodeRepository.saveAll(Arrays.asList(discountCode1, discountCode2));
    }
}
