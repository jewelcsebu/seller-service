package com.jewelcse045.sellerservice.controller;


import com.jewelcse045.sellerservice.exception.ApplicationException;
import com.jewelcse045.sellerservice.model.Seller;
import com.jewelcse045.sellerservice.service.SellerServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SellerController {

    private SellerServiceImp sellerServiceImp;

    @Autowired
    public SellerController(SellerServiceImp serviceImp){
        this.sellerServiceImp = serviceImp;
    }

    @GetMapping("/state")
    public String state(){
        return "running";
    }

    @PostMapping("/seller/create")
    public ResponseEntity<Seller> saveSeller(@RequestBody Seller seller) throws RuntimeException{

        if (seller.getSellerFirstName() == null || seller.getSellerFirstName().isEmpty()){
            throw new  ApplicationException("Seller First Name can't be empty");
        }

        if (seller.getSellerLastName() == null || seller.getSellerLastName().isEmpty()){
            throw new  ApplicationException("Seller Last Name can't be empty");
        }

        if (seller.getEmail() == null || seller.getEmail().isEmpty()){
            throw new  ApplicationException("Email can't be empty");
        }

        if (seller.getContactNumber() == null || seller.getContactNumber().isEmpty()){
            throw new  ApplicationException("Contact Number can't be empty");
        }

        if (seller.getAddress() == null || seller.getAddress().length==0){
            throw new  ApplicationException("Address can't be empty");
        }

        boolean doesExit = sellerServiceImp.getSellerByEmail(seller.getEmail());
        if (doesExit){
            throw new ApplicationException("Seller exit with that email "+ seller.getEmail());
        }

        return new ResponseEntity<>(sellerServiceImp.saveOrUpdateSeller(seller),HttpStatus.CREATED);
    }
}
