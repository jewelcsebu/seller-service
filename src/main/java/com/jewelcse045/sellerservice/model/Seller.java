package com.jewelcse045.sellerservice.model;


import lombok.*;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "sellers")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;
    private String sellerFirstName;
    private String sellerLastName;
    private String email;
    private String contactNumber;
    private String address;



}
