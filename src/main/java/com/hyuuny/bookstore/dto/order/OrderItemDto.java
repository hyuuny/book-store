package com.hyuuny.bookstore.dto.order;

import com.hyuuny.bookstore.domain.OrderItem;
import lombok.Getter;

public class OrderItemDto {

  @Getter
  public static class OrderItemResponse {

    private String itemName;
    public int orderPrice;
    private int count;

    public OrderItemResponse(OrderItem orderItem) {
      this.itemName = orderItem.getItem().getName();
      this.orderPrice = orderItem.getOrderPrice();
      this.count = orderItem.getCount();
    }
  }

}
