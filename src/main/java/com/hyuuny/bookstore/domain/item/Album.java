package com.hyuuny.bookstore.domain.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@DiscriminatorValue("A")
@Setter
@Getter
@Entity
public class Album extends Item {

  private String artist;
  private String etc;

}
