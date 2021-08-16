package com.hyuuny.bookstore.repository.order.query;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class OrderItemQueryDto {

  private Long orderId;
  private String itemName;
  private int orderPrice;
  private int count;

}
