package com.baas.service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "baas_table")
public class BaaS {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "userid")
    private String userId;

    @Column(name = "baas_key")
    private String key;

    @Column(name = "baas_value")
    private Boolean value;

}