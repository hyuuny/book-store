package com.hyuuny.bookstore.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import com.hyuuny.bookstore.domain.Member;
import com.hyuuny.bookstore.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class MemberServiceTest {

  @Autowired
  private MemberService memberService;

  @Autowired
  private MemberRepository memberRepository;


  @DisplayName("회원 가입")
  @Test
  void join() throws Exception {
    // given
    Member member = new Member();
    member.setName("member");

    // when
    Long savedId = memberService.join(member);

    // then
    assertThat(member).isEqualTo(memberRepository.findOne(savedId));
  }

  @DisplayName("중복 회원 예외")
  @Test
  public void duplicateName() throws Exception {

    // given
    Member member1 = new Member();
    member1.setName("member");

    Member member2 = new Member();
    member2.setName("member");

    // when
    memberService.join(member1);
    try {
      memberService.join(member2);
    } catch (IllegalStateException e) {
      return;
    }

    // then
    fail("예외가 발생한다.");
  }

}