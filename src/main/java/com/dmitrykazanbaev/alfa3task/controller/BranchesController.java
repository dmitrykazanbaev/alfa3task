package com.dmitrykazanbaev.alfa3task.controller;

import com.dmitrykazanbaev.alfa3task.exception.NotFoundBranchException;
import com.dmitrykazanbaev.alfa3task.model.Branch;
import com.dmitrykazanbaev.alfa3task.model.BranchWithDistance;
import com.dmitrykazanbaev.alfa3task.model.BranchWithPrediction;
import com.dmitrykazanbaev.alfa3task.service.BranchCrudRepository;
import com.dmitrykazanbaev.alfa3task.service.QueueLogCrudRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.math3.stat.descriptive.rank.Median;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping("branches")
@RequiredArgsConstructor
public class BranchesController {

    private final BranchCrudRepository repository;
    private final QueueLogCrudRepository queueLogRepository;

    @GetMapping("{branchId}")
    public Branch getBranchById(@PathVariable("branchId") Integer branchId) {
        return repository.findById(branchId)
                .orElseThrow(NotFoundBranchException::new);
    }

    @GetMapping
    public BranchWithDistance getBranchWithLatLon(@RequestParam("lat") BigDecimal lat, @RequestParam("lon") BigDecimal lon) {
        return repository.getClosestBranch(lat, lon);
    }


    @GetMapping("{branchId}/predict")
    public BranchWithPrediction predict(@PathVariable("branchId") Integer branchId,
                                        @RequestParam("dayOfWeek") Integer dayOfWeek, @RequestParam("hourOfDay") Integer hourOfDay) {
        Branch branch = getBranchById(branchId);
        List<Integer> seconds = queueLogRepository.predict(dayOfWeek, hourOfDay, branchId);
        return new BranchWithPrediction(
                branch,
                dayOfWeek,
                hourOfDay,
                Math.round(new Median().evaluate(seconds.stream().mapToDouble(i -> i).toArray()))
        );
    }
}
