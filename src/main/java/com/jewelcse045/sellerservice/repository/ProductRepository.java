package com.jewelcse045.sellerservice.repository;


import com.jewelcse045.sellerservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {


    List<Product> findAllBySellerId(int sellerId);
}
