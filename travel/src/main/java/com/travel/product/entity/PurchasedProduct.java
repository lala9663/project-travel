package com.travel.product.entity;

import com.travel.order.dto.response.OrderResponseDTO;
import com.travel.order.entity.Order;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "purchased_product")
@Entity
public class PurchasedProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchased_product_id")
    private Long purchasedProductId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "purchased_product_name")
    private String purchasedProductName;

    @Column(name = "purchased_product_thumbnail")
    private String purchasedProductThumbnail;

    @Column(name = "purchased_product_price")
    private Integer purchasedProductPrice;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "period")
    private Integer period;

    @Column(name = "option_name")
    private String optionName;

    @Setter
    @Column(name = "purchased_product_quantity")
    private Integer productProductQuantity = 1;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Builder
    public PurchasedProduct(Product product, PeriodOption periodOption) {
        this.product = product;
        this.purchasedProductName = product.getProductName();
        this.purchasedProductThumbnail = product.getProductThumbnail();
        this.purchasedProductPrice = product.getProductPrice(); //나중에 옵션이랑 더 해줄 예정
        this.startDate = periodOption.getStartDate();
        this.endDate = periodOption.getEndDate();
        this.period = periodOption.getPeriod();
        this.optionName = periodOption.getOptionName();
    }

    public OrderResponseDTO toOrderResponseDTO() {
        return OrderResponseDTO.builder()
                .orderId(this.order.getOrderId())
                .productId(this.product.getProductId())
                .productName(this.purchasedProductName)
                .productThumbnail(this.purchasedProductThumbnail)
                .productPrice(this.purchasedProductPrice)
                .optionName(this.optionName)
                .productProductQuantity(this.productProductQuantity)
                .orderDate(this.order.getCreatedDate())
                .isCanceled(this.order.getIsCanceled())
                .build();
    }
}
