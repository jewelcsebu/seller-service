package com.jewelcse045.sellerservice.service;


import com.jewelcse045.sellerservice.dto.VoucherDto;
import com.jewelcse045.sellerservice.model.Voucher;

import java.util.List;


public interface VoucherService {
    Voucher createVoucher(VoucherDto voucherDto);

    List<Voucher> getVouchers(int sellerId);
}
