package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) { //멤버리포지토리를 외부에서 넣어주게 생성함 (DI)
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member){
        validateDuplicateMember(member); //중복 회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
        .ifPresent(m->{
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /**
     * 전체 화원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /**
     * 회원 한 명 조회
     */
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
