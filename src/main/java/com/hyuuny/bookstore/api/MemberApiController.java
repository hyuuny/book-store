package com.hyuuny.bookstore.api;

import com.hyuuny.bookstore.domain.Member;
import com.hyuuny.bookstore.dto.member.CreateMemberDto.CreateMemberRequest;
import com.hyuuny.bookstore.dto.member.CreateMemberDto.CreateMemberResponse;
import com.hyuuny.bookstore.dto.member.GetMemberDto.GetMemberResponse;
import com.hyuuny.bookstore.dto.member.GetMemberDto.Result;
import com.hyuuny.bookstore.dto.member.UpdateMemberDto.UpdateMemberRequest;
import com.hyuuny.bookstore.dto.member.UpdateMemberDto.UpdateMemberResponse;
import com.hyuuny.bookstore.service.MemberService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class MemberApiController {

  private final MemberService memberService;


  @GetMapping("/api/v1/members")
  public List<Member> membersV1() {
    return memberService.findMembers();
  }

  @GetMapping("/api/v2/members")
  public Result membersV2() {
    List<GetMemberResponse> getMemberResponses = memberService.findMembers().stream()
        .map(this::memberResponse)
        .collect(Collectors.toList());
    return new Result(getMemberResponses, getMemberResponses.size());
  }

  private GetMemberResponse memberResponse(Member member) {
    return new GetMemberResponse(member.getName());
  }

  @PostMapping("/api/v1/members")
  public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
    Long savedId = memberService.join(member);
    return new CreateMemberResponse(savedId);
  }

  @PostMapping("/api/v2/members")
  public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {
    Member member = new Member();
    member.setName(request.getName());
    Long savedId = memberService.join(member);
    return new CreateMemberResponse(savedId);
  }

  @PutMapping("/api/v2/members/{id}")
  public UpdateMemberResponse updateMemberV2(
      @PathVariable final Long id,
      @RequestBody @Valid UpdateMemberRequest request
  ) {
    memberService.update(id, request.getName());
    Member existingMember = memberService.findOne(id);
    return new UpdateMemberResponse(existingMember.getId(), existingMember.getName());
  }


}
