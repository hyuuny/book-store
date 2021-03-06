package com.hyuuny.bookstore.controller;

import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberForm {

  @NotEmpty(message = "회원 이름은 필수 입력입니다.")
  private String name;

  private String city;
  private String street;
  private String zipcode;

}
