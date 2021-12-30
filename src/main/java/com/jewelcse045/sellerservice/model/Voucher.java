package com.jewelcse045.sellerservice.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "vouchers")
public class Voucher {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voucherId;
    private String voucherName;
    private String voucherDescription;
    private String voucherCode;
    private Double discountAmount;
    private Date voucherExpirationDate;
    private Double voucherMinExpenditure;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Seller seller;


}
