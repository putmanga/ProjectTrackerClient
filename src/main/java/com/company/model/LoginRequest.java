package com.company.model;

import com.company.validation.annotations.Email;
import com.company.validation.annotations.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginRequest {
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email format is incorrect")
    private String email;


    @NotBlank(message = "Password cannot be blank")
    private String password;
}
