package com.hyuuny.bookstore.domain;

import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Getter
public class Address {

  private String city;
  private String street;
  private String zipcode;

}
