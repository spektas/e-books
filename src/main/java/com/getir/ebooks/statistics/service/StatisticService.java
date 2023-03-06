package com.getir.ebooks.statistics.service;

import com.getir.ebooks.statistics.model.MonthlyOrderStatisticDTO;

import java.util.List;

public interface StatisticService {
    List<MonthlyOrderStatisticDTO> getMonthlyStatistics();
}
