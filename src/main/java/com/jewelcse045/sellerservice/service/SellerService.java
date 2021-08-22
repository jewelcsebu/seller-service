package com.jewelcse045.sellerservice.service;


import com.jewelcse045.sellerservice.model.Product;
import com.jewelcse045.sellerservice.model.Seller;
import org.springframework.data.domain.Page;

import java.awt.*;
import java.util.List;
import java.util.Optional;


public interface SellerService {

    Seller saveOrUpdateSeller(Seller seller);
    List<Seller> getSellers();
    List<Seller> getSellersWithSorting(String field);
    Page<Seller> getSellersWithPagination(int offset, int pageSize);
    Page<Seller> getSellersWithPaginationAndSorting(int offset, int pageSize, String field);
    boolean  getSellerByEmail(String email);
    Optional<Seller> getSellerById(int id);
    void removeSeller(Seller seller);
    List<Product> getProductsBySellerId(int sellerId);

}
