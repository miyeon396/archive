package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        //1. Persistence에서 시작 2. EntityManagerFactroy 만듦
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");//persistence.xml의 name으로 작성해놓은 것. 설정 정보의 설정파일 읽어와서 만듦.
        //EntityManagerFactory는 애플리케이션 로딩 시점에 딱 한개만 만들어야함.

        //세팅까지 완료 된 것임.
        EntityManager em = emf.createEntityManager(); //쿼리할때마다 EntityManager 만들어줘야함.
        //실제 디비 값 불러온다던가 저장한다던가를 여기서 함.

        //모든 데이터를 변경하는 단위의 작업은 jpa는 꼭 트랜잭션 안에서 해줘야함
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //입력
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("coco");
//            em.persist(member); //난 쿼리를 해준게 없고 매핑정보를 보고 자기가 넣어준 것임.

            //조회
//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember.id"+findMember.getId());
//            System.out.println("findMember.name"+findMember.getName());

            //삭제
//            em.remove(findMember); //찾은애를 넣어주면 됨

            //수정
//            findMember.setName("miyeonJpa"); //자바 컬렉션을 다루듯 설계 되었기 때문에 persist안해주고 저 setName만 자바 객체 값만 바꿔주면 업뎃 됨
            //jpa가 체크해서 트랜잭션 커밋 전에 체크해서 변경사항이 있으면 업뎃 쿼리 딱 날리고 커밋함

            //2. JPQL
            //em.createQuery해서 쿼리를 칠 수 있음. jpa는 테이블 대상으로 쿼리 하지 않음. 대상(Member)을 다가져 옴 == 대상이 테이블이 아닌 객체가 되어야함
            List<Member> resultList = em.createQuery("select m from Member as m", Member.class)
                    .getResultList();

            for (Member member: resultList) {
                System.out.println("member.name="+member.getName());
            }

            //실제 실행 된 것은 필드 다 나열. 근데 jpql보면 멤버 엔티티만 선택 했다.
            //페이징 .setFirstResult(1) .setMaxResults(10) 1번부터 10개 가져와
            // -> 객체를 대상으로 한 객체지향 쿼리. 방언에 맞춰서 여러가지들을 디비에 맞게 번역을 해줌.

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); //내부적으로 db 커넥션 물고 동작하기 떄문에 보고 닫아줘야함.
        }
        //첫 예제라 일단은 일케 보여주지만 실제는 스프링이 이걸 다 해주니까 실무는 좀 달름

        //실제 애플리케이션이 완벽하게 끝나는 경우.
        emf.close();
    }
}
