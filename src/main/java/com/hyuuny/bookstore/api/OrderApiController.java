package com.hyuuny.bookstore.api;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

import com.hyuuny.bookstore.domain.Order;
import com.hyuuny.bookstore.dto.order.OrderDto.OrderResponse;
import com.hyuuny.bookstore.repository.OrderRepository;
import com.hyuuny.bookstore.repository.OrderSearch;
import com.hyuuny.bookstore.repository.order.query.OrderFlatDto;
import com.hyuuny.bookstore.repository.order.query.OrderItemQueryDto;
import com.hyuuny.bookstore.repository.order.query.OrderQueryDto;
import com.hyuuny.bookstore.repository.order.query.OrderQueryRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class OrderApiController {

  private final OrderRepository orderRepository;
  private final OrderQueryRepository orderQueryRepository;


  @GetMapping("/api/v1/orders")
  public List<Order> orderV1() {
    List<Order> orders = orderRepository.findCriteria(new OrderSearch());
    for (Order order : orders) {
      order.getMember().getName();
      order.getDelivery().getAddress();
      order.getOrderItems().stream()
          .forEach(o -> o.getItem().getName());
    }
    return orders;
  }

  @GetMapping("/api/v2/orders")
  public List<OrderResponse> orderV2() {
    return orderRepository.findCriteria(new OrderSearch()).stream()
        .map(OrderResponse::new)
        .collect(toList());
  }

  @GetMapping("/api/v3/orders")
  public List<OrderResponse> orderV3() {
    return orderRepository.findAllWithItems().stream()
        .map(OrderResponse::new)
        .collect(toList());
  }

  @GetMapping("/api/v3.1/orders")
  public List<OrderResponse> orderV3Page(
      @RequestParam(value = "offset", defaultValue = "0") final int offset,
      @RequestParam(value = "limit", defaultValue = "100") final int limit
  ) {
    return orderRepository.findAllWithMemberDelivery(offset, limit).stream()
        .map(OrderResponse::new)
        .collect(toList());
  }

  @GetMapping("/api/v4/orders")
  public List<OrderQueryDto> orderV4() {
    return orderQueryRepository.findOrderQueryDtos();
  }

  @GetMapping("/api/v5/orders")
  public List<OrderQueryDto> orderV5() {
    return orderQueryRepository.findAllByDto();
  }

  @GetMapping("/api/v6/orders")
  public List<OrderQueryDto> orderV6() {
    List<OrderFlatDto> flats = orderQueryRepository.findAllByDtoFlat();

    return flats.stream()
        .collect(groupingBy(o -> new OrderQueryDto(o.getOrderId(), o.getName(), o.getOrderDate(),
                o.getOrderStatus(), o.getAddress()),
            mapping(o -> new OrderItemQueryDto(o.getOrderId(), o.getItemName(), o.getOrderPrice(),
                o.getCount()), toList())
        )).entrySet().stream()
        .map(e -> new OrderQueryDto(e.getKey().getOrderId(), e.getKey().getName(),
            e.getKey().getOrderDate(), e.getKey().getOrderStatus(), e.getKey().getAddress(),
            e.getValue()))
        .collect(Collectors.toList());
  }

}
