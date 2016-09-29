package com.multitenancy.example.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.jackson.JsonSnakeCase;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name="tests")
@JsonSnakeCase
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    @Column
    private Long id;

    @JsonProperty
    @Column(name="name", nullable = false)
    @NotEmpty
    private String name;

    @JsonProperty
    @Column(name="description", nullable = false)
    @NotEmpty
    private String description;

    public Test() {
    }

    public Test(Long id, String name, String description, String tenantId) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
