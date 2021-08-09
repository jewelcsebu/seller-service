package com.jewelcse045.sellerservice.model;


import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "sellers")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;
    private String sellerFirstName;
    private String sellerLastName;
    private String email;
    private String contactNumber;
    private String address[];


}
