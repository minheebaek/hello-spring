package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {
    //스프링한테 멤버서비스, 멤버리포지토리 내놔 해야됨
    //어떻게? DI 하기 근데 테스트는 끝단에 있는거라 편한 방법 사용함 @Autowired 사용
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository; //구현체는 SpringConfig에서 올라올거임

    @Test
    void 회원가입() { //테스트는 한글로 해도 상관무
        //given
        Member member = new Member();
        member.setName("spring1");

        //when 검증할거
        Long saveId = memberService.join(member);

        //then
        //우리가 가입해서 저장한게 리포지토리에 있는게 맞아? 검증하고 싶은 것임
        Member findMember=memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");

        Member member2 = new Member();
        member2.setName("spring1");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));//이 예외가 터져야하고, 이 로직을 따를때
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }
}