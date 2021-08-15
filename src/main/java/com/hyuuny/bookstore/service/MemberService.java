package com.hyuuny.bookstore.service;

import com.hyuuny.bookstore.domain.Member;
import com.hyuuny.bookstore.repository.MemberRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {

  private final MemberRepository memberRepository;

  @Transactional
  public Long join(final Member member) {
    validateDuplicateMember(member);
    memberRepository.save(member);
    return member.getId();
  }

  private void validateDuplicateMember(final Member member) {
    List<Member> findMembers = memberRepository.findByName(member.getName());
    if (!findMembers.isEmpty()) {
      throw new IllegalStateException("이미 존재하는 회원입니다.");
    }
  }

  public List<Member> findMembers() {
    return memberRepository.findAll();
  }

  public Member findOne(final Long id) {
    return memberRepository.findOne(id);
  }

  @Transactional
  public void update(final Long id, final String name) {
    Member existingMember = memberRepository.findOne(id);
    existingMember.setName(name);
  }
}
