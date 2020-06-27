package com.dmitrykazanbaev.alfa3task.service;

import com.dmitrykazanbaev.alfa3task.model.QueueLog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QueueLogCrudRepository extends CrudRepository<QueueLog, Integer> {

    @Query("select \n" +
            "  EXTRACT(\n" +
            "    EPOCH \n" +
            "    FROM \n" +
            "      (\n" +
            "        end_time_of_wait - start_time_of_wait\n" +
            "      )\n" +
            "  ) as seconds \n" +
            "from \n" +
            "  QueueLog \n" +
            "where \n" +
            "  :data = EXTRACT(\n" +
            "    ISODOW \n" +
            "    FROM \n" +
            "      data\n" +
            "  ) \n" +
            "  and :hour = EXTRACT(\n" +
            "    HOUR \n" +
            "    FROM \n" +
            "      start_time_of_wait\n" +
            "  ) \n" +
            "  and branches_id = :id")
    List<Integer> predict(@Param("data") Integer data, @Param("hour") Integer hour, @Param("id") Integer id);
}
