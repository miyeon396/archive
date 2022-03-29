package hellojpa;

import javax.persistence.*;
import java.util.Date;

@Entity
public class MemberBackup2 {
    @Id
    private Long id; //pk 매핑

    @Column(name = "name")
    private String username; //db 컬럼명은 name, java에선 username

    private Integer age;

    @Enumerated(EnumType.STRING) //자바에서 enum 타입 쓰고 싶은데 db엔 없지만 Enumerated 쓰면됨
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP) //TemporalType 3가지가 있음 DATE, TIME, TIMESTAMP
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob //varchar를 넘어서는 큰 값을 넣고 싶을 때
    private String description; //문자 타입이면 자동으로 clob으로 생성

    @Transient
    private int temp; //db랑 관계 없이 메모리에서만 하고 싶을 때
}
