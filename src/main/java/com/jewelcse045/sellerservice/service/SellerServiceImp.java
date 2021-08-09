package com.jewelcse045.sellerservice.service;

import com.jewelcse045.sellerservice.model.Product;
import com.jewelcse045.sellerservice.model.Seller;
import com.jewelcse045.sellerservice.repository.ProductRepository;
import com.jewelcse045.sellerservice.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SellerServiceImp implements SellerService{

    private SellerRepository sellerRepository;
    private ProductRepository productRepository;


    public SellerServiceImp(SellerRepository sellerRepository,ProductRepository productRepository){
        this.sellerRepository = sellerRepository;
        this.productRepository = productRepository;
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

    @Override
    public List<Seller> getSellers() {
        return sellerRepository.findAll();
    }

    @Override
    public Optional<Seller> getSellerById(int id) {
        Optional<Seller> seller = sellerRepository.findById(id);
        return seller;
    }

    @Override
    public void removeSeller(Seller seller) {
        sellerRepository.delete(seller);
    }

    @Override
    public List<Product> getProductsBySellerId(int sellerId) {
        return productRepository.findAllBySellerId(sellerId);
    }


}
