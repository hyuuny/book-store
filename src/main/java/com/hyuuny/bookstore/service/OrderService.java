package com.hyuuny.bookstore.service;

import com.hyuuny.bookstore.domain.Delivery;
import com.hyuuny.bookstore.domain.Member;
import com.hyuuny.bookstore.domain.Order;
import com.hyuuny.bookstore.domain.OrderItem;
import com.hyuuny.bookstore.domain.item.Item;
import com.hyuuny.bookstore.repository.ItemRepository;
import com.hyuuny.bookstore.repository.MemberRepository;
import com.hyuuny.bookstore.repository.OrderRepository;
import com.hyuuny.bookstore.repository.OrderSearch;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class OrderService {

  private final OrderRepository orderRepository;
  private final MemberRepository memberRepository;
  private final ItemRepository itemRepository;

  @Transactional
  public Long order(final Long memberId, final Long itemId, final int count) {
    Member member = memberRepository.findOne(memberId);
    Item item = itemRepository.findOne(itemId);

    Delivery delivery = new Delivery();
    delivery.setAddress(member.getAddress());

    OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
    Order order = Order.createOrder(member, delivery, orderItem);
    orderRepository.save(order);
    return order.getId();
  }

  @Transactional
  public void cancelOrder(final Long id) {
    Order order = orderRepository.findOne(id);
    order.cancel();
  }

  public List<Order> findOrders(OrderSearch orderSearch) {
    return orderRepository.findCriteria(orderSearch);
  }

}
