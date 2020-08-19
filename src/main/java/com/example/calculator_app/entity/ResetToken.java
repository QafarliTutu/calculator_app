package com.example.calculator_app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private long id;

    private String token;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date creationDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "user_id")
    private XUser xUser;


    public ResetToken(XUser existingUser) {
        this.xUser = existingUser;
        creationDate = new Date();
        token = UUID.randomUUID().toString();
    }
}
