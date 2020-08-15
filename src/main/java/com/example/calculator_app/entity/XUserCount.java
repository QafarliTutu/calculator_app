package com.example.calculator_app.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class XUserCount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "count_id")
    private long id;

    private long maxCount;

    private long currCount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id")
    private XUser user;

}
