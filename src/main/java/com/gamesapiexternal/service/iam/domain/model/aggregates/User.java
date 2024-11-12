package com.gamesapiexternal.service.iam.domain.model.aggregates;

import com.gamesapiexternal.service.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * User aggregate root
 * This class represents the aggregate root for the User entity.
 *
 * @see AuditableAbstractAggregateRoot
 */
@Getter
@Setter
@Entity(name = "users")
@NoArgsConstructor
public class User extends AuditableAbstractAggregateRoot<User> {
    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    @Email
    private String username;

    @NotBlank
    @Size(max = 120)
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
