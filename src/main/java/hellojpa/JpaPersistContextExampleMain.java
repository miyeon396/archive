package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaPersistContextExampleMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            Member member1 = new Member(150L, "A");
//            Member member2 = new Member(160L, "B");
//
//            em.persist(member1);
//            em.persist(member2);
//
//            System.out.println("=============="); //요게 찍히고 나서 insert 쿼리가 찍힘. commit하고 한방에 insert 모아서 치는 것.

            //수정
            MemberBackup member = em.find(MemberBackup.class, 150L);
            member.setName("ZZZZ"); //jpa 목적은 콜렉션 다루듯이.. 찾아서 변경하면 알아서 수정됨.
            System.out.println("============");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
