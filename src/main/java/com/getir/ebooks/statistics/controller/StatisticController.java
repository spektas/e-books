package com.getir.ebooks.statistics.controller;

import com.getir.ebooks.statistics.model.MonthlyOrderStatisticDTO;
import com.getir.ebooks.statistics.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("statistics")
public class StatisticController {
    private final StatisticService statisticService;

    @GetMapping("/orders/monthly")
    public ResponseEntity<List<MonthlyOrderStatisticDTO>> getMonthlyStatistics() {
        return new ResponseEntity(statisticService.getMonthlyStatistics(), HttpStatus.OK);
    }
}
