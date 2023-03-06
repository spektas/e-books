package com.getir.ebooks.statistics.repository;

import com.getir.ebooks.statistics.entity.MonthlyOrderStatistic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticRepository extends CrudRepository<MonthlyOrderStatistic, Long> {
    Iterable<MonthlyOrderStatistic> findAll();
}
