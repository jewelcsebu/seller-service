package com.jewelcse045.sellerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class VoucherDto {

    private String voucherName;
    private String voucherDescription;
    private Double discountAmount;
    private LocalDate voucherExpirationDate;
    private Double voucherMinExpenditure;
}
