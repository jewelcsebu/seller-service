package com.jewelcse045.sellerservice.service;

import com.jewelcse045.sellerservice.model.Seller;
import com.jewelcse045.sellerservice.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class SellerServiceImp implements SellerService{

    private SellerRepository sellerRepository;

    @Autowired
    public SellerServiceImp(SellerRepository repository){
        this.sellerRepository = repository;
    }

    @Override
    public Seller saveOrUpdateSeller(Seller seller) {
        return sellerRepository.save(seller);
    }

    @Override
    public boolean getSellerByEmail(String email) {
       Optional<Seller> seller = sellerRepository.findByEmail(email);

       if (!seller.isEmpty()){
           return true;
       }

       return false;
    }


}
