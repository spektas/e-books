package com.getir.ebooks.statistics.service.impl;

import com.getir.ebooks.common.constant.AppConstant;
import com.getir.ebooks.statistics.repository.StatisticRepository;
import com.getir.ebooks.statistics.entity.MonthlyOrderStatistic;
import com.getir.ebooks.statistics.model.MonthlyOrderStatisticDTO;
import com.getir.ebooks.statistics.service.StatisticService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {
    private final StatisticRepository statisticRepository;

    public StatisticServiceImpl(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    @Override
    public List<MonthlyOrderStatisticDTO> getMonthlyStatistics() {
        List<MonthlyOrderStatisticDTO> monthlyOrderStatistics = new ArrayList<>();
        for (MonthlyOrderStatistic monthlyOrderStatistic : statisticRepository.findAll()) {
            MonthlyOrderStatisticDTO monthlyOrderStatisticDTO = new MonthlyOrderStatisticDTO();
            monthlyOrderStatisticDTO.setMonthName(AppConstant.monthMap.get(monthlyOrderStatistic.getMonthNumber()));
            monthlyOrderStatisticDTO.setTotalBookCount(monthlyOrderStatistic.getTotalBookCount());
            monthlyOrderStatisticDTO.setTotalOrderCount(monthlyOrderStatistic.getTotalOrderCount());
            monthlyOrderStatisticDTO.setTotalPurchasedAmount(monthlyOrderStatistic.getTotalPurchasedAmount());
            monthlyOrderStatistics.add(monthlyOrderStatisticDTO);
        }
        return monthlyOrderStatistics;
    }
}
