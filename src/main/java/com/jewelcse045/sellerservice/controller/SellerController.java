package com.jewelcse045.sellerservice.controller;


import com.jewelcse045.sellerservice.exception.ApplicationException;
import com.jewelcse045.sellerservice.exception.SellerNotFoundException;
import com.jewelcse045.sellerservice.model.Seller;
import com.jewelcse045.sellerservice.service.SellerService;
import com.jewelcse045.sellerservice.service.SellerServiceImp;
import com.jewelcse045.sellerservice.util.JsonResponseEntityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seller-service/v1/")
public class SellerController {

    JsonResponseEntityModel responseModel = new JsonResponseEntityModel();

    private SellerService sellerService;

    @Autowired
    public SellerController(SellerService sellerService){
        this.sellerService = sellerService;
    }

    @GetMapping("/state")
    public String state(){
        return "running";
    }

    @PostMapping(path = "/create/seller", produces = { MediaType.APPLICATION_JSON_VALUE })
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

        if (seller.getAddress() == null){
            throw new  ApplicationException("Address can't be empty");
        }

        boolean doesExit = sellerService.getSellerByEmail(seller.getEmail());
        if (doesExit){
            throw new ApplicationException("Seller exit with that email "+ seller.getEmail());
        }

        return new ResponseEntity<>(sellerService.saveOrUpdateSeller(seller),HttpStatus.CREATED);
    }


    @GetMapping(path = "/get/sellers", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<JsonResponseEntityModel> getSellers(){

        List<Seller> data = sellerService.getSellers();

        responseModel.setSuccess(true);
        responseModel.setData(data);
        responseModel.setDataSize(data.size());
        responseModel.setStatusCode("200");

        return new ResponseEntity<>(responseModel,HttpStatus.OK);
    }

    @GetMapping(path = "/get/sellers/{field}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<JsonResponseEntityModel> getSellersByField(@PathVariable("field") String field){

        List<Seller> data = sellerService.getSellersWithSorting(field);
        responseModel.setSuccess(true);
        responseModel.setData(data);
        responseModel.setDataSize(data.size());
        responseModel.setStatusCode("200");
        return new ResponseEntity<>(responseModel,HttpStatus.OK);
    }

    @GetMapping(path = "/get/sellers/{offset}/{pageSize}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<JsonResponseEntityModel> getSellersByPageSizeAndOffset(@PathVariable int offset,@PathVariable int pageSize){

        Page<Seller> data = sellerService.getSellersWithPagination(offset,pageSize);
        responseModel.setSuccess(true);
        responseModel.setData(data);
        responseModel.setDataSize(data.getSize());
        responseModel.setStatusCode("200");
        return new ResponseEntity<>(responseModel,HttpStatus.OK);
    }

    @GetMapping(path = "/get/sellers/{offset}/{pageSize}/{field}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<JsonResponseEntityModel> getSellersBySortingAndPagination(
            @PathVariable int offset,
            @PathVariable int pageSize,
            @PathVariable String field){

        Page<Seller> data = sellerService.getSellersWithPaginationAndSorting(offset,pageSize,field);
        responseModel.setSuccess(true);
        responseModel.setData(data);
        responseModel.setDataSize(data.getSize());
        responseModel.setStatusCode("200");
        return new ResponseEntity<>(responseModel,HttpStatus.OK);
    }




    @GetMapping(path = "/get/seller/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<JsonResponseEntityModel> getSeller(@PathVariable int id){

        Seller seller = sellerService.getSellerById(id).orElseThrow(()->new SellerNotFoundException("Seller Not Found for Id: "+id));

        responseModel.setSuccess(true);
        responseModel.setData(seller);
        responseModel.setStatusCode("200");

        return new ResponseEntity<>(responseModel,HttpStatus.OK);
    }



    @PutMapping(path = "/update/seller/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<JsonResponseEntityModel> updateSeller(@RequestBody Seller seller,@PathVariable int id){
        if (id<=0){
            throw new ApplicationException("Invalid Seller Id"+id);
        }
        Seller seller1 = sellerService.getSellerById(id)
                .orElseThrow( () ->new SellerNotFoundException("Seller Not found for id "+id));
        seller.setId(id);
        responseModel.setSuccess(true);
        responseModel.setData(sellerService.saveOrUpdateSeller(seller));
        responseModel.setStatusCode("200");

        return new ResponseEntity<>(responseModel,HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/seller/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public JsonResponseEntityModel removeSeller(@PathVariable int id){
        if (id<=0){
            throw new ApplicationException("Invalid Seller Id "+id);
        }
        Seller seller1 = sellerService.getSellerById(id)
                .orElseThrow( () ->new SellerNotFoundException("Seller Not found for id "+id));


        try {
            sellerService.removeSeller(seller1);
            responseModel.setSuccess(true);
            responseModel.setData("Successfully delete seller for id "+id);
            responseModel.setStatusCode("200");
        }catch (Exception e){
            responseModel.setSuccess(false);
            responseModel.setData("Not able to delete seller for id "+id);
            responseModel.setStatusCode("403");
        }
        return responseModel;
    }

//    @GetMapping(path = "/get/seller/{sellerId}/products", produces = { MediaType.APPLICATION_JSON_VALUE })
//    public ResponseEntity<JsonResponseEntityModel> getSellerProducts(@PathVariable int sellerId){
//
//        if (sellerId<=0){
//            throw new ApplicationException("Invalid Seller Id");
//        }
//
//        Seller doesSellerExit = sellerService.getSellerById(sellerId)
//                .orElseThrow( () ->new SellerNotFoundException("Seller Not found for id "+sellerId));
//
//
//        responseModel.setSuccess(true);
//        responseModel.setData(sellerService.getProductsBySellerId(sellerId));
//        responseModel.setStatusCode("200");
//
//        return new ResponseEntity<>(responseModel,HttpStatus.OK);
//    }

    
}
