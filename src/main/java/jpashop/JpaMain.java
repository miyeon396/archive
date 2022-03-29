package jpashop;

import hellojpa.MemberBackup;
import jpashop.domain.Member;
import jpashop.domain.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Order order = em.find(Order.class, 1L);
            Long memberId = order.getMemberId();

            Member member = em.find(Member.class, memberId);
            //오더에서 주문한 멤버를 찾아서 그 멤버를 뒤져서 아이디를 찾는다? -> 객체지향적이지 않음. -> 관계형 디비에 맞춘 설계..

            Member member1 = order.getMember();
            //여기서 이렇게 바로바로 찾아 들어가는게 좋지..

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}