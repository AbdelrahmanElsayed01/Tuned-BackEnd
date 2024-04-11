package com.example.tuned.Persistence.Entity;

import com.example.tuned.Business.enums.SubscriptionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "Users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long userId;

    @NotBlank
    @Column(name = "name")
    @Length(min = 2, max = 100)
    private String name;

    @NotBlank
    @Column(name = "username")
    @Length(min = 2, max = 100)
    private String username;

    @NotBlank
    @Email
    @Column(name = "email")
    private String email;

    @NotBlank
    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private SubscriptionType subscription;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private PlaylistEntity playlist;

}