package com.ebooks.productservice.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
public class OrderResponseDto {
    private Long bookId;
    private int quantity;
    private BigDecimal totalPrice;
    private String status;

    @Override
    public String toString() {
        return "OrderResponseDto{" +
                "bookId=" + bookId +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                ", status='" + status + '\'' +
                '}';
    }
}
