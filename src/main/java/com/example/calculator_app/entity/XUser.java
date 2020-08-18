package com.example.calculator_app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class XUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String fullname;
    private String password;
    private String ref_user_email;
    private String roles;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private XUserCount userCount;

    @OneToMany(mappedBy = "xUser",cascade = CascadeType.ALL)
    private List<History> history;

    @Transient
    private final static String DELIMITER = ":";

    public XUser(String username, String password, String... roles) {
        this.username = username;
        this.password = password;
        setRoles(roles);
    }

    public String[] getRoles() {
        return roles == null || roles.isEmpty() ? new String[]{}
                : roles.split(DELIMITER);
    }

    public void setRoles(String[] roles) {
        this.roles = String.join(DELIMITER, roles);
    }
}
