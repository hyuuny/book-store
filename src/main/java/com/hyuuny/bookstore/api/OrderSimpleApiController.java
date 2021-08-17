package com.hyuuny.bookstore.api;

import static java.util.stream.Collectors.toList;

import com.hyuuny.bookstore.domain.Order;
import com.hyuuny.bookstore.dto.order.OrderDto.OrderSimpleDto;
import com.hyuuny.bookstore.dto.order.OrderDto.OrderSimpleQueryDto;
import com.hyuuny.bookstore.repository.OrderRepository;
import com.hyuuny.bookstore.repository.OrderSearch;
import com.hyuuny.bookstore.repository.order.simplequery.OrderSimpleQueryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class OrderSimpleApiController {

  private final OrderRepository orderRepository;
  private final OrderSimpleQueryRepository orderSimpleQueryRepository;


  @GetMapping("/api/v1/simple-orders")
  public List<Order> orderV1() {
    return orderRepository.findCriteria(new OrderSearch());
  }

  @GetMapping("/api/v2/simple-orders")
  public List<OrderSimpleDto> orderV2() {
    return orderRepository.findCriteria(new OrderSearch()).stream()
        .map(OrderSimpleDto::new)
        .collect(toList());
  }

  /**
   * fetch join 적용 (가능한 Entity를 조회하도록 하자.)
   */
  @GetMapping("/api/v3/simple-orders")
  public List<OrderSimpleDto> orderV3() {
    return orderRepository.findAllWithMemberDelivery().stream()
        .map(OrderSimpleDto::new)
        .collect(toList());
  }

  /**
   * Dto로 조회
   */
  @GetMapping("/api/v4/simple-orders")
  public List<OrderSimpleQueryDto> orderV4() {
    return orderSimpleQueryRepository.findOrderDtos();
  }

}
