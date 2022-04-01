package com.example.backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long memberNo;

    @Column(length = 32, nullable = false)
    private String memberName;

    @Column(length = 32, nullable = false)
    private String memberId;

    @Column(length = 64, nullable = false)
    private String password;

    @CreationTimestamp
    private Date regDate;

    @UpdateTimestamp
    private Date updDate;

    public Member(String memberName, String memberId, String password) {
        this.memberName = memberName;
        this.memberId = memberId;
        this.password = password;
    }
}
