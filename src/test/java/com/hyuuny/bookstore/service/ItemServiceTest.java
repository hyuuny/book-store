package com.hyuuny.bookstore.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.hyuuny.bookstore.domain.item.Book;
import com.hyuuny.bookstore.domain.item.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class ItemServiceTest {

  @Autowired
  private ItemService itemService;


  @DisplayName("아이템 저장")
  @Test
  void saveItem() throws Exception {

    // given
    Book book = new Book();
    book.setAuthor("hyuuny");
    book.setIsbn("123123123");

    // when
    itemService.saveItem(book);

    // then
    assertThat(book).isEqualTo(itemService.findItems().get(0));
  }

}