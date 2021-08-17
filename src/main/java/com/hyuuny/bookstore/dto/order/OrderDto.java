package com.hyuuny.bookstore.dto.order;

import com.hyuuny.bookstore.domain.Address;
import com.hyuuny.bookstore.domain.Order;
import com.hyuuny.bookstore.domain.OrderStatus;
import com.hyuuny.bookstore.dto.order.OrderItemDto.OrderItemResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

public class OrderDto {

  @AllArgsConstructor
  @Data
  public static class OrderSimpleDto {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    public OrderSimpleDto(Order order) {
      this.orderId = order.getId();
      this.name = order.getMember().getName();
      this.orderDate = order.getOrderDate();
      this.orderStatus = order.getStatus();
      this.address = order.getDelivery().getAddress();
    }
  }

  @Data
  public static class OrderSimpleQueryDto {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    public OrderSimpleQueryDto(Long orderId, String name, LocalDateTime orderDate,
        OrderStatus orderStatus, Address address) {
      this.orderId = orderId;
      this.name = name;
      this.orderDate = orderDate;
      this.orderStatus = orderStatus;
      this.address = address;
    }
  }

  @Getter
  public static class OrderResponse {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;
    private List<OrderItemResponse> orderItems;

    public OrderResponse(Order order) {
      this.orderId = order.getId();
      this.name = order.getMember().getName();
      this.orderDate = order.getOrderDate();
      this.orderStatus = order.getStatus();
      this.address = order.getDelivery().getAddress();
      this.orderItems = order.getOrderItems().stream()
          .map(OrderItemResponse::new)
          .collect(Collectors.toList());
    }
  }

}
