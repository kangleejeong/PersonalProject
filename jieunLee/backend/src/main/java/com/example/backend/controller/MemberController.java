package com.example.backend.controller;

import com.example.backend.entity.Member;
import com.example.backend.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/member")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class MemberController {

    @Autowired
    private MemberService service;

    @GetMapping("/list")
    public List<Member> memberList() {
        log.info("memberList()");

        return service.list();
    }

    @PostMapping("/search")
    public List<Member> search (@RequestBody KeyWordRequest keyWord) {
        log.info("search()"+keyWord);

        return service.search(keyWord.getKeyWord());
    }

    @PostMapping("/register")
    public void MemberRegister(@Validated @RequestBody MemberRequest memberRequest) {
        log.info("MemberRegister():" + "," +
                memberRequest.getMemberName() + ", " +
                memberRequest.getMemberId() + ", " +
                memberRequest.getPassword());
        service.register(memberRequest);
    }

    @PostMapping("/login")
    public MemberRequest memberLogin (@RequestBody MemberRequest memberRequest) {
        log.info("memberLogin(): " + memberRequest);

        MemberRequest memberResponse = service.login(memberRequest);

        if (memberResponse != null) {
            log.info("로그인 성공!");
        } else {
            log.info("로그인 실패!");
        }

        return memberResponse;
    }

    @GetMapping("/{memberNo}")
    public Member memberRead(@PathVariable("memberNo") Long memberNo) {
        log.info("memberRead()");

        return service.read(memberNo);
    }

    @PutMapping("/{memberNo}")
    public Member profileModify (
            @PathVariable("memberNo") Long memberNo,
            @RequestBody Member member) {
        log.info("memberModify(): " + member);

        member.setMemberNo(Long.valueOf(memberNo));
        service.profile(member);

        return member;
    }

    @PutMapping("/pw/{memberNo}")
    public Member passwordModify (
            @PathVariable("memberNo") Long memberNo,
            @RequestBody Member member) {
        log.info("memberModify(): " + member);

        member.setMemberNo(Long.valueOf(memberNo));
        service.modify(member);

        return member;
    }

    @ResponseBody
    @PostMapping("/uploadImg")
    public String requestUploadFile (@RequestParam("fileList") List<MultipartFile> fileList) {
        try {
            for (MultipartFile multipartFile: fileList) {
                log.info("requestUploadFile() - Make file:" + multipartFile.getOriginalFilename());

                FileOutputStream writer = new FileOutputStream(
                        "../frontend/src/assets/mImage/" + multipartFile.getOriginalFilename()
                );
                writer.write(multipartFile.getBytes());
                writer.close();
            }
        } catch (Exception e) {
            return "Upload Fail!";
        }

        log.info("requestUploadFile(): Success!!!");

        return "Upload Success!";
    }

    @DeleteMapping("/{memberNo}")
    public void memberRemove (
            @PathVariable("memberNo") Long memberNo) {
        log.info("memberRemove()");


        service.remove(memberNo);
    }
}
