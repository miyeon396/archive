package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity //jpa가 처음 로딩될 때 관리해야할 클래스인거 알고 관리
//@Table(name="USER") //만약 테이블 네임이 다르다면 table annotation 사용 -> user라는 테이블에 나가게됨
public class MemberBackup {

    //jpa에게 pk가 뭔지 알려줘야함
    @Id
    private Long id;
//    @Column(name = "username") //칼럼이 다르다면 칼럼 어노테이션으로 재지정
    private String name;

    public MemberBackup(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public MemberBackup() { //기본 생성자 추가해줘야함

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
