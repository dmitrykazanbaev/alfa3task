package com.dmitrykazanbaev.alfa3task.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
public class BranchWithDistance extends Branch {
    @Column
    private Long distance;
}
