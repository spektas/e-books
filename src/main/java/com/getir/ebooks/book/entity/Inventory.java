package com.getir.ebooks.book.entity;

import com.getir.ebooks.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Audited
@EntityListeners({AuditingEntityListener.class})
@Getter
@Setter
@Entity
@Table(name = "inventory")
public class Inventory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Book book;

    @Version
    private int version;

    private Integer quantity;

}
