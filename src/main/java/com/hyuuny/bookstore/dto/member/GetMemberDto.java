package com.hyuuny.bookstore.dto.member;

import lombok.AllArgsConstructor;
import lombok.Data;

public class GetMemberDto {

  @AllArgsConstructor
  @Data
  public static class Result<T> {

    private T data;
    private int count;
  }

  @AllArgsConstructor
  @Data
  public static class GetMemberResponse{

    private String name;
  }


}
