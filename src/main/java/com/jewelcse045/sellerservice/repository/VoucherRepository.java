package com.jewelcse045.sellerservice.repository;

import com.jewelcse045.sellerservice.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VoucherRepository extends JpaRepository<Voucher,Long> {

    List<Voucher> findAllBySellerId(int sellerId);
}
