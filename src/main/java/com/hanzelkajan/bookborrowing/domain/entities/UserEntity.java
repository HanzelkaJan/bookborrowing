package com.hanzelkajan.bookborrowing.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="users_id_seq")
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
