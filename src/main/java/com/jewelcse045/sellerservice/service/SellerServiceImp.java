package com.jewelcse045.sellerservice.service;

import com.jewelcse045.sellerservice.model.Seller;
import com.jewelcse045.sellerservice.repository.SellerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SellerServiceImp implements SellerService{

    private SellerRepository sellerRepository;

//    @PostConstruct
//    public void initDB() {
//        List<Seller> sellers = IntStream.rangeClosed(1, 200)
//                .mapToObj(i -> new Seller(i,"sellerFirstName " + i,"sellerLastName "+ i,"email "+ i,"0193079266"+i,"address "+ i))
//                .collect(Collectors.toList());
//        sellerRepository.saveAll(sellers);
//    }


    public SellerServiceImp(SellerRepository sellerRepository){
        this.sellerRepository = sellerRepository;
    }

    @Override
    public Seller saveOrUpdateSeller(Seller seller) {
        return sellerRepository.save(seller);
    }

    @Override
    public boolean getSellerByEmail(String email) {
       Optional<Seller> seller = sellerRepository.findByEmail(email);

       if (seller ==null){
           return true;
       }

       return false;
    }

    @Override
    public List<Seller> getSellers() {
        return sellerRepository.findAll();
    }
    @Override
    public List<Seller> getSellersWithSorting(String field){
        return sellerRepository.findAll(Sort.by(Sort.Direction.DESC,field));
    }

    @Override
    public Page<Seller> getSellersWithPagination(int offset,int pageSize){
        return sellerRepository.findAll(PageRequest.of(offset,pageSize));
    }

    @Override
    public Page<Seller> getSellersWithPaginationAndSorting(int offset,int pageSize,String field){
        return sellerRepository.findAll(PageRequest.of(offset,pageSize).withSort(Sort.by(field)));
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



}
