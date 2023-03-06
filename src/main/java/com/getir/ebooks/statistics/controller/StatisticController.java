package com.getir.ebooks.statistics.controller;

import com.getir.ebooks.statistics.model.MonthlyOrderStatisticDTO;
import com.getir.ebooks.statistics.service.StatisticService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StatisticController {
    private final StatisticService statisticService;

    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping("statistic/orders/monthly")
    public ResponseEntity<List<MonthlyOrderStatisticDTO>> getMonthlyStatistics() {
        return new ResponseEntity(statisticService.getMonthlyStatistics(), HttpStatus.OK);
    }
}
