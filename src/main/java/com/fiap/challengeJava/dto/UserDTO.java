package com.fiap.challengeJava.dto;

import com.fiap.challengeJava.domain.User;
import com.fiap.challengeJava.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    @NotBlank
    private String name;
    private String rg;
    private LocalDate birthDate;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
    private UserRole role;
    private String cro;
    private Long clinicId;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.rg = user.getRg();
        this.birthDate = user.getBirthDate();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.cro = user.getCro();
        this.clinicId = user.getClinic().getId();
    }

    public UserDTO(String name, String email, String rg, LocalDate birthDate, String password, UserRole role, String cro, Long clinicId) {
        this.name = name;
        this.email = email;
        this.rg = rg;
        this.birthDate = birthDate;
        this.password = password;
        this.role = role;
        this.cro = cro;
        this.clinicId = clinicId;
    }

    public UserDTO(String name, String email, String rg, LocalDate birthDate, String password, UserRole role, Long clinicId) {
        this.name = name;
        this.email = email;
        this.rg = rg;
        this.birthDate = birthDate;
        this.password = password;
        this.role = role;
        this.clinicId = clinicId;
    }
}
