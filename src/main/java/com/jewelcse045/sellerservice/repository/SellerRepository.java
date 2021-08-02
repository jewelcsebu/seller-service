package com.jewelcse045.sellerservice.repository;


import com.jewelcse045.sellerservice.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {

    Optional<Seller> findByEmail(String email);
}
