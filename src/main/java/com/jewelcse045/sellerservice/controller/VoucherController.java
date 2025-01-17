package com.jewelcse045.sellerservice.controller;


import com.jewelcse045.sellerservice.dto.VoucherDto;
import com.jewelcse045.sellerservice.model.Voucher;
import com.jewelcse045.sellerservice.service.VoucherService;
import com.jewelcse045.sellerservice.util.JsonResponseEntityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/seller-service")
public class VoucherController {


    JsonResponseEntityModel responseModel = new JsonResponseEntityModel();


    private VoucherService voucherService;

    @Autowired
    public VoucherController(VoucherService voucherService){
        this.voucherService = voucherService;
    }

    @PostMapping("/create/voucher")
    public ResponseEntity<Voucher> createVoucher(@RequestBody VoucherDto voucherDto){
        return new ResponseEntity<Voucher>(voucherService.createVoucher(voucherDto), HttpStatus.CREATED);
    }

    @GetMapping("/get/vouchers/{sellerId}")
    ResponseEntity<List<Voucher>> getSellerVouchers(@PathVariable int sellerId){
        return new ResponseEntity<>(voucherService.getVouchers(sellerId),HttpStatus.OK);
    }




}
