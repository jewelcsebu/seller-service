package com.jewelcse045.sellerservice.controller;


import com.jewelcse045.sellerservice.exception.ApplicationException;
import com.jewelcse045.sellerservice.exception.SellerNotFoundException;
import com.jewelcse045.sellerservice.model.Seller;
import com.jewelcse045.sellerservice.service.SellerServiceImp;
import com.jewelcse045.sellerservice.util.JsonResponseEntityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seller-service/v1/")
public class SellerController {

    JsonResponseEntityModel responseModel = new JsonResponseEntityModel();

    private SellerServiceImp sellerServiceImp;

    @Autowired
    public SellerController(SellerServiceImp serviceImp){
        this.sellerServiceImp = serviceImp;
    }

    @GetMapping("/state")
    public String state(){
        return "running";
    }

    @PostMapping("/create/seller")
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


    @GetMapping("/get/sellers")
    public ResponseEntity<JsonResponseEntityModel> getSellers(){

        responseModel.setSuccess(true);
        responseModel.setData(sellerServiceImp.getSellers());
        responseModel.setStatusCode("200");

        return new ResponseEntity<>(responseModel,HttpStatus.OK);
    }

    @GetMapping("/get/seller/{id}")
    public ResponseEntity<JsonResponseEntityModel> getSeller(@PathVariable int id){

        Seller seller = sellerServiceImp.getSellerById(id).orElseThrow(()->new SellerNotFoundException("Seller Not Found for Id: "+id));

        responseModel.setSuccess(true);
        responseModel.setData(seller);
        responseModel.setStatusCode("200");

        return new ResponseEntity<>(responseModel,HttpStatus.OK);
    }



    @PutMapping("/update/seller/{id}")
    public ResponseEntity<JsonResponseEntityModel> updateSeller(@RequestBody Seller seller,@PathVariable int id){
        if (id<=0){
            throw new ApplicationException("Invalid Seller Id"+id);
        }
        Seller seller1 = sellerServiceImp.getSellerById(id)
                .orElseThrow( () ->new SellerNotFoundException("Seller Not found for id "+id));
        seller.setId(id);
        responseModel.setSuccess(true);
        responseModel.setData(sellerServiceImp.saveOrUpdateSeller(seller));
        responseModel.setStatusCode("200");

        return new ResponseEntity<>(responseModel,HttpStatus.OK);
    }

    @DeleteMapping("/delete/seller/{id}")
    public JsonResponseEntityModel removeSeller(@PathVariable int id){
        if (id<=0){
            throw new ApplicationException("Invalid Seller Id "+id);
        }
        Seller seller1 = sellerServiceImp.getSellerById(id)
                .orElseThrow( () ->new SellerNotFoundException("Seller Not found for id "+id));


        try {
            sellerServiceImp.removeSeller(seller1);
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
}
