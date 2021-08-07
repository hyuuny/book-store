package com.hyuuny.bookstore.domain.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@DiscriminatorValue("B")
@Setter
@Getter
@Entity
public class Book extends Item {

  private String author;
  private String isbn;

}
