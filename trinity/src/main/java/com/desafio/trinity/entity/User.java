package com.desafio.trinity.entity;

import com.desafio.trinity.controller.dto.UserDTO;
import com.desafio.trinity.controller.response.UserResponse;
import com.desafio.trinity.util.PasswordUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private LocalDate birthday;

    private String login;

    private String password;

    private String phone;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Car> cars;

    public User(UserDTO userDTO){
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.email = userDTO.getEmail();
        this.birthday = userDTO.getBirthday();
        this.login = userDTO.getLogin();
        this.password = PasswordUtil.gerarBCrypt(userDTO.getPassword());
        this.phone = userDTO.getPhone();
    }

    public UserResponse fromResponse(){
        return UserResponse.builder()
                .id(this.id)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .birthday(this.birthday)
                .email(this.email)
                .login(this.login)
                .phone(this.phone).build();
    }
}
