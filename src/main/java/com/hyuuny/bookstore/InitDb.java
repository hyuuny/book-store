package com.hyuuny.bookstore;

import com.hyuuny.bookstore.domain.Address;
import com.hyuuny.bookstore.domain.Delivery;
import com.hyuuny.bookstore.domain.Member;
import com.hyuuny.bookstore.domain.Order;
import com.hyuuny.bookstore.domain.OrderItem;
import com.hyuuny.bookstore.domain.item.Book;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 샘플 데이터 설정
 */
@RequiredArgsConstructor
@Component
public class InitDb {

  private final InitService initService;

  @PostConstruct
  public void init() {
    initService.dbInit1();
    initService.dbInit2();
  }


  @Transactional
  @RequiredArgsConstructor
  @Component
  static class InitService {

    private final EntityManager em;

    public void dbInit1() {
      Member member = createMember("userA", "서울 ", "종로", "11111");
      em.persist(member);

      Book book1 = createBook("JPA1", 10000, 100);
      em.persist(book1);

      Book book2 = createBook("JPA2", 20000, 100);
      em.persist(book2);

      OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
      OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

      Delivery delivery = createDelivery(member);

      Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
      em.persist(order);
    }

    private Delivery createDelivery(Member member) {
      Delivery delivery = new Delivery();
      delivery.setAddress(member.getAddress());
      return delivery;
    }

    private Book createBook(String name, int price, int stockQuantity) {
      Book book1 = new Book();
      book1.setName(name);
      book1.setPrice(price);
      book1.setStockQuantity(stockQuantity);
      return book1;
    }

    private Member createMember(String name, String city, String street, String zipcode) {
      Member member = new Member();
      member.setName(name);
      member.setAddress(new Address(city, street, zipcode));
      return member;
    }

    public void dbInit2() {
      Member member = createMember("userB", "부산 ", "해운대로", "22222");
      em.persist(member);

      Book book1 = createBook("Spring1", 20000, 200);
      em.persist(book1);

      Book book2 = createBook("Spring2", 40000, 300);
      em.persist(book2);

      OrderItem orderItem1 = OrderItem.createOrderItem(book1, 20000, 3);
      OrderItem orderItem2 = OrderItem.createOrderItem(book2, 40000, 4);

      Delivery delivery = createDelivery(member);

      Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
      em.persist(order);
    }

  }

}

