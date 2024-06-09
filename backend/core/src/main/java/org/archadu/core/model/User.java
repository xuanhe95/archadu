package org.archadu.core.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

@Entity
public record User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id,
        String username,
        String password
) implements Serializable {
    public User (){
        this(null, null, null);
    }
}
