package com.jewelcse045.sellerservice.service;


import com.jewelcse045.sellerservice.model.Seller;
import org.springframework.stereotype.Service;

public interface SellerService {

    public Seller saveOrUpdateSeller(Seller seller);


    boolean  getSellerByEmail(String email);
}
