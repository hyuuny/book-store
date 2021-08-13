package com.hyuuny.bookstore.repository;

import com.hyuuny.bookstore.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderSearch {

  private String memberName;
  private OrderStatus orderStatus;

}
