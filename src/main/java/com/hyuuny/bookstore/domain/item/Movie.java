package com.hyuuny.bookstore.domain.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@DiscriminatorValue("M")
@Setter
@Getter
@Entity
public class Movie extends Item {

  private String director;
  private String actor;

}
