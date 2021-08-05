package com.jewelcse045.sellerservice.service;


import com.jewelcse045.sellerservice.model.Seller;

import java.awt.*;
import java.util.List;
import java.util.Optional;


public interface SellerService {

    Seller saveOrUpdateSeller(Seller seller);
    boolean  getSellerByEmail(String email);

    List<Seller> getSellers();

    Optional<Seller> getSellerById(int id);

    void removeSeller(Seller seller);
}
