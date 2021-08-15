package com.hyuuny.bookstore.dto.member;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UpdateMemberDto {

  @NoArgsConstructor
  @AllArgsConstructor
  @Data
  public static class UpdateMemberRequest{
    @NotEmpty
    private String name;
  }

  @NoArgsConstructor
  @AllArgsConstructor
  @Data
  public static class UpdateMemberResponse{

    private Long id;
    private String name;
  }

}
