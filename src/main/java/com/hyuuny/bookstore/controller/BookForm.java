package com.hyuuny.bookstore.controller;

import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookForm {

  private Long id;

  @NotEmpty(message = "도서 이름은 필수 입력입니다.")
  private String name;

  private int price;
  private int stockQuantity;
  private String author;
  private String isbn;


}
