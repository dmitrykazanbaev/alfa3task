package com.dmitrykazanbaev.alfa3task.controller;

import com.dmitrykazanbaev.alfa3task.exception.NotFoundBranchException;
import com.dmitrykazanbaev.alfa3task.model.Branch;
import com.dmitrykazanbaev.alfa3task.model.BranchWithDistance;
import com.dmitrykazanbaev.alfa3task.service.BranchCrudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("branches")
@RequiredArgsConstructor
public class BranchesController {

    private final BranchCrudRepository repository;

    @GetMapping("{branchId}")
    public Branch getBranchById(@PathVariable("branchId") Integer branchId) {
        return repository.findById(branchId)
                .orElseThrow(NotFoundBranchException::new);
    }

    @GetMapping
    public BranchWithDistance getBranchWithLatLon(@RequestParam("lat") BigDecimal lat, @RequestParam("lon") BigDecimal lon) {
        return repository.getClosestBranch(lat, lon);
    }
}
