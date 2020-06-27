package com.dmitrykazanbaev.alfa3task.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "branches")
@Data
public class Branch {
    @Id
    private Integer id;
    @Column
    private String title;
    @Column
    private BigDecimal lon;
    @Column
    private BigDecimal lat;
    @Column
    private String address;
}
