package co.istad.ite2mbanking.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable=false)
    private String uuid;
    @Column(length = 60)
    private String name;
    @Column(length = 8 )
    private String gender;
    @Column(unique = true)
    private String oneSingleId;
    @Column(unique = true )
    private String studentIdCard;

    private  Boolean isStudent;
    private  Boolean isDelete;
    @OneToMany(mappedBy = "user")
    private List<UserAccount> accounts;

}
