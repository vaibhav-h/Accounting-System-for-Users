package com.accounting.system.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private Long locationId;
    @Column(nullable = false)
    private Long zipCode;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String city;

    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY)
    private List<Transaction> transactions;

}
