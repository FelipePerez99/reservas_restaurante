package com.app.reservasR.domain.entity;

import com.app.reservasR.application.lasting.ERole;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "\"user\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Boolean enable;

    @Enumerated(EnumType.ORDINAL)
    private ERole role;

}
