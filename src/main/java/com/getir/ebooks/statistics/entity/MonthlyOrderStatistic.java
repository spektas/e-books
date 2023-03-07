package com.getir.ebooks.statistics.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "monthly_order_statistic_view")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class MonthlyOrderStatistic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "total_order_count")
    private BigDecimal totalOrderCount;

    @Column(name = "total_book_count")
    private Long totalBookCount;

    @Column(name = "total_purchased_amount")
    private BigDecimal totalPurchasedAmount;

    @Column(name = "month")
    private Integer monthNumber;

}
