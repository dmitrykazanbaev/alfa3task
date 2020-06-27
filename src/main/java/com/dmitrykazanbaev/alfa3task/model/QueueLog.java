package com.dmitrykazanbaev.alfa3task.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "queue_log")
@Data
public class QueueLog {
    @Column
    private LocalDate data;
    @Column
    private LocalTime startTimeOfWait;
    @Column
    private LocalTime endTimeOfWait;
    @Column
    private LocalTime endTimeOfService;
    @ManyToOne(targetEntity = Branch.class)
    private Integer branchesId;
    @Id
    private Integer id;
}
