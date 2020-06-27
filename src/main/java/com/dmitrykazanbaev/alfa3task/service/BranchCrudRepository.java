package com.dmitrykazanbaev.alfa3task.service;

import com.dmitrykazanbaev.alfa3task.model.Branch;
import com.dmitrykazanbaev.alfa3task.model.BranchWithDistance;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface BranchCrudRepository extends CrudRepository<Branch, Integer> {
    @Query(value = "select \n" +
            "  br.*, \n" +
            "  round(\n" +
            "    2 * 6371000 * asin(\n" +
            "      sqrt(\n" +
            "        power(\n" +
            "          sin(\n" +
            "            (radians(:givenLat) - radians(br.lat))/ 2.0\n" +
            "          ), \n" +
            "          2.0\n" +
            "        )+ cos(radians(:givenLat))* cos(radians(br.lat))* power(\n" +
            "          sin(\n" +
            "            (radians(:givenLon) - radians(br.lon))/ 2.0\n" +
            "          ), \n" +
            "          2.0\n" +
            "        )\n" +
            "      )\n" +
            "    )\n" +
            "  ) as distance \n" +
            "from \n" +
            "  branches br \n" +
            "order by \n" +
            "  distance \n" +
            "limit \n" +
            "  1",
    nativeQuery = true)
    BranchWithDistance getClosestBranch(@Param("givenLat") BigDecimal givenLat, @Param("givenLon") BigDecimal givenLon);
}
