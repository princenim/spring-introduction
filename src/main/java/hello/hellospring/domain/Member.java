package hello.hellospring.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * @author hazel
 */

@Entity
//jpa 매핑
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    //db가 알아서 생성해줌
    private Long id;
    private String name;


    //getter,setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
