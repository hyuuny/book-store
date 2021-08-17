package com.hyuuny.bookstore.repository.order.query;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class OrderQueryRepository {

  private final EntityManager em;

  public List<OrderQueryDto> findOrderQueryDtos() {
    List<OrderQueryDto> orders = findOrders();
    orders.forEach(o -> {
      List<OrderItemQueryDto> orderItems = findOrderItems(o.getOrderId());
      o.setOrderItems(orderItems);
    });
    return orders;
  }

  private List<OrderItemQueryDto> findOrderItems(final Long orderId) {
    return em.createQuery(
            "select new com.hyuuny.bookstore.repository.order.query.OrderItemQueryDto(oi.order.id, i.name, oi.orderPrice, oi.count)"
                + " from OrderItem oi"
                + " join oi.item i"
                + " where oi.order.id = :orderId", OrderItemQueryDto.class)
        .setParameter("orderId", orderId)
        .getResultList();
  }

  private List<OrderQueryDto> findOrders() {
    return em.createQuery(
            "select new com.hyuuny.bookstore.repository.order.query.OrderQueryDto(o.id, m.name, o.orderDate, o.status, d.address)"
                + " from Order o"
                + " join o.member m"
                + " join o.delivery d", OrderQueryDto.class)
        .getResultList();
  }

  public List<OrderQueryDto> findAllByDto() {
    List<OrderQueryDto> orders = findOrders();
    List<Long> orderIds = toOrderIds(orders);
    Map<Long, List<OrderItemQueryDto>> orderItemMap = findOrderItemMap(orderIds);
    orders.forEach(o -> o.setOrderItems(orderItemMap.get(o.getOrderId())));

    return orders;
  }

  private Map<Long, List<OrderItemQueryDto>> findOrderItemMap(List<Long> orderIds) {
    List<OrderItemQueryDto> orderItems = em.createQuery(
            "select new com.hyuuny.bookstore.repository.order.query.OrderItemQueryDto(oi.order.id, i.name, oi.orderPrice, oi.count)"
                + " from OrderItem oi"
                + " join oi.item i"
                + " where oi.order.id in :orderIds", OrderItemQueryDto.class)
        .setParameter("orderIds", orderIds)
        .getResultList();

    return orderItems.stream()
        .collect(Collectors.groupingBy(OrderItemQueryDto::getOrderId));
  }

  private List<Long> toOrderIds(List<OrderQueryDto> orders) {
    return orders.stream()
        .map(OrderQueryDto::getOrderId)
        .collect(Collectors.toList());
  }

  public List<OrderFlatDto> findAllByDtoFlat() {
    return em.createQuery(
            "select new"
                + " com.hyuuny.bookstore.repository.order.query.OrderFlatDto(o.id, m.name, o.orderDate, o.status, d.address, i.name, oi.orderPrice, oi.count)"
                + " from Order o"
                + " join o.member m"
                + " join o.delivery d"
                + " join o.orderItems oi"
                + " join oi.item i", OrderFlatDto.class)
        .getResultList();
  }
}
