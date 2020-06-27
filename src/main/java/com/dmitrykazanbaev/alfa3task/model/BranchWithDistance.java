package com.dmitrykazanbaev.alfa3task.model;

import java.math.BigDecimal;

public interface BranchWithDistance {
    Integer getId();

    String getTitle();

    BigDecimal getLon();

    BigDecimal getLat();

    String getAddress();

    Long getDistance();
}
