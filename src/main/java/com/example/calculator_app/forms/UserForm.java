package com.example.calculator_app.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {
    private String fullname;
    private String username;
    private String password;
    private String passwordConfirm;
    private String ref_user_email;
}
