package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    /* 스프링 JdbcTemplate 강의 실습 코드
    //JdbcMemberRepository는 dataSource가 필요하다 이건 스프링이 제공해준다
    //스프링부트가 프로퍼티즈 보고 자체적으로 dataSource 만들고 DI 해준다.
    private final DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
*/
    /*JPA 강의 실습 코드
    private EntityManager em;

    public SpringConfig(EntityManager em) {
        this.em = em;
    }
*/
    //스프링데이터 JPA 강의 실습 코드
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean //스프링빈 등록할거야
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository(){

        //return new MemoryMemberRepository(); //인터페이스는 new 안됨
        //return new JdbcMemberRepository(dataSource);
        //return new JdbcTemplateMemberRepository(dataSource);
        //return new JpaMemberRepository(em);
//    }

}
