package org.archadu.core.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class Track {
    @Id
    private String trackId;
}
