package com.example.calculator_app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String expression;
    private String result;
    private LocalDateTime date;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",nullable = false)
    private XUser xUser;

    public History(String expression, String result, LocalDateTime date, XUser user) {
        this.expression = expression;
        this.result = result;
        this.date = date;
        this.xUser = user;
    }
}
