package com.example.demo.service.presonalProject;

import com.example.demo.controller.request.MemberDto;
import com.example.demo.entity.personalProject.Member;

import java.util.Optional;

public interface MemberService {
    public Member register(MemberDto memberDto) throws Exception;
    public boolean login (Member member) throws Exception;
    boolean validationId(String id) throws Exception;
    Optional<Member> userInfo(String id) throws Exception;
    Member findById(String id) throws Exception;
    void modify(Member member, MemberDto memberDto) throws Exception;
    void remove(Member member) throws Exception;
}
