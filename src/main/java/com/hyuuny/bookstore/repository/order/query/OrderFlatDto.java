package com.hyuuny.bookstore.repository.order.query;

import com.hyuuny.bookstore.domain.Address;
import com.hyuuny.bookstore.domain.OrderStatus;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class OrderFlatDto {

  private Long orderId;
  private String name;
  private LocalDateTime orderDate;
  private OrderStatus orderStatus;
  private Address address;

  private String itemName;
  private int orderPrice;
  private int count;

}
