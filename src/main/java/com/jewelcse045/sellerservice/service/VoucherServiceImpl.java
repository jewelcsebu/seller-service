package com.jewelcse045.sellerservice.service;

import com.jewelcse045.sellerservice.dto.CustomPrincipal;
import com.jewelcse045.sellerservice.dto.VoucherDto;
import com.jewelcse045.sellerservice.model.Voucher;
import com.jewelcse045.sellerservice.repository.VoucherRepository;
import com.jewelcse045.sellerservice.util.MethodUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VoucherServiceImpl implements VoucherService{


    private VoucherRepository voucherRepository;

    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    public VoucherServiceImpl(VoucherRepository voucherRepository){
        this.voucherRepository = voucherRepository;
    }

    @Override
    public Voucher createVoucher(VoucherDto voucherDto) {
        Voucher voucher = new Voucher();
        voucher.setVoucherName(voucherDto.getVoucherName());
        voucher.setVoucherDescription(voucherDto.getVoucherDescription());
        voucher.setVoucherExpirationDate(voucherDto.getVoucherExpirationDate());
        voucher.setVoucherMinExpenditure(voucherDto.getVoucherMinExpenditure());
        voucher.setDiscountAmount(voucherDto.getDiscountAmount());
        voucher.setSellerId(getUser().getId());
        voucher.setVoucherCode(MethodUtils.generateVoucherCode());
        return voucherRepository.save(voucher);
    }

    @Override
    public List<Voucher> getVouchers(int sellerId) {
        return voucherRepository.findAllBySellerId(sellerId);
    }

    private CustomPrincipal getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String, String> vars = new HashMap<>();
        vars.put("username", authentication.getPrincipal().toString());
        return restTemplate.getForObject("http://localhost:9191/api/v1/auth-service/user/{username}",CustomPrincipal.class,vars);
    }
}
