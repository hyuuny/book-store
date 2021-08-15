package com.hyuuny.bookstore.dto.member;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CreateMemberDto {

  @NoArgsConstructor
  @AllArgsConstructor
  @Data
  public static class CreateMemberRequest {

    @NotEmpty(message = "이름은 필수 입력입니다.")
    private String name;
  }

  @NoArgsConstructor
  @AllArgsConstructor
  @Data
  public static class CreateMemberResponse {
    private Long id;
  }

}


