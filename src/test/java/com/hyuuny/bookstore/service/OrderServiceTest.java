package com.hyuuny.bookstore.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import com.hyuuny.bookstore.domain.Address;
import com.hyuuny.bookstore.domain.Member;
import com.hyuuny.bookstore.domain.Order;
import com.hyuuny.bookstore.domain.OrderItem;
import com.hyuuny.bookstore.domain.OrderStatus;
import com.hyuuny.bookstore.domain.item.Book;
import com.hyuuny.bookstore.domain.item.Item;
import com.hyuuny.bookstore.exception.NotEnoughStockException;
import com.hyuuny.bookstore.repository.OrderRepository;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class OrderServiceTest {

  @Autowired
  private OrderService orderService;

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private EntityManager em;

  @DisplayName("주문")
  @Test
  void order() throws Exception {

    // given
    Member member = createMember();

    Book book = createBook("JPA", 10000, 10);

    int orderCount = 2;

    // when
    Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

    // then
    Order findOrder = orderRepository.findOne(orderId);

    assertThat(findOrder.getStatus()).isEqualTo(OrderStatus.ORDER);
    assertThat(findOrder.getOrderItems().size()).isEqualTo(1);
    assertThat(findOrder.getTotalPrice()).isEqualTo(book.getPrice() * orderCount);
    assertThat(book.getStockQuantity()).isEqualTo(8);
  }

  private Book createBook(String name, int price, int stockQuantity) {
    Book book = new Book();
    book.setName(name);
    book.setPrice(price);
    book.setStockQuantity(stockQuantity);
    em.persist(book);
    return book;
  }

  private Member createMember() {
    Member member = new Member();
    member.setName("회원1");
    member.setAddress(new Address("서울", "경기", "12345"));
    em.persist(member);
    return member;
  }

  @DisplayName("주문 취소")
  @Test
  void cancelOrder() throws Exception {

    // given
    Member member = createMember();
    Book item = createBook("JPA", 10000, 10);
    int orderCount = 2;
    Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

    // when
    orderService.cancelOrder(orderId);

    // then
    Order findOrder = orderRepository.findOne(orderId);

    assertThat(findOrder.getStatus()).isEqualTo(OrderStatus.CANCEL);
    assertThat(item.getStockQuantity()).isEqualTo(10);
  }

  @DisplayName("재고수량 초과 예외")
  @Test
  void manyQuantityException() throws Exception {

    // given
    Member member = createMember();
    Item item = createBook("JPA", 10000, 10);
    int orderCount = 11;

    // when
    try {
      orderService.order(member.getId(), item.getId(), orderCount);
    } catch (NotEnoughStockException e) {
      return;
    }

    // then
    fail("재고 수량 부족 예외가 발생한다.");
  }


}