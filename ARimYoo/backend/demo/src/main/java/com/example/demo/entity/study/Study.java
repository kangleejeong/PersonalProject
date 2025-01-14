package com.example.demo.entity.study;

import com.example.demo.entity.communityBoard.CommunityBoard;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Study {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyNo;

    @Column
    private String studyName;

    @Column
    private String firstMember;

    @Column
    private int people;

    @Column
    private String fileName;

    @Column
    private String openLink;

    @Column
    private String toDo;

    public Study(String studyName, String firstMember, int people, Long studyNo, String fileName, String openLink) {
        this.studyNo = studyNo;
        this.studyName = studyName;
        this.firstMember = firstMember;
        this.people = people;
        this.fileName = fileName;
        this.openLink = openLink;
    }

}
