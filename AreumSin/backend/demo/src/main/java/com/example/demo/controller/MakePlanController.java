package com.example.demo.controller;

import com.example.demo.entity.Member;
import com.example.demo.entity.MemberPlan;
import com.example.demo.entity.Vote;
import com.example.demo.request.*;
import com.example.demo.response.FriendMemberResponse;
import com.example.demo.response.MemberListResponse;
import com.example.demo.response.PlanResponse;
import com.example.demo.response.VoteResponse;
import com.example.demo.service.join.JoinMemberService;
import com.example.demo.service.plan.MakePlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/plan")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class MakePlanController {

    @Autowired
    private MakePlanService planService;

    @Autowired
    private JoinMemberService memberService;

    @PostMapping("/makePlan")
    public MemberPlanRequest makePlan(@Validated @RequestBody MemberPlanRequest memberPlanRequest){
        log.info("makePlan()");

        planService.make(memberPlanRequest);

        return memberPlanRequest;
    }

    @PostMapping("/list/{id}")
    public List<PlanResponse> userPlanList(@PathVariable("id") String id){
        log.info("userPlanList() " + id);

        int memberNo = memberService.memberNo(id);

        List<MemberPlan> userPlans = planService.listPlan(memberNo);
        List<PlanResponse> responses = new ArrayList<>();

        for(MemberPlan memberPlan : userPlans){
            log.info("test" + memberPlan.getPlan().getPlanName() + " ," +  memberPlan.getPlan().getPlanDate()+ " ," + memberPlan.getPlan().getPlaceName());
            responses.add(new PlanResponse(memberPlan.getPlan().getPlanNo(), memberPlan.getPlan().getPlanName(), memberPlan.getPlan().getPlanDate(), memberPlan.getPlan().getPlaceName()));
        }

        //planService.listPlan(id);

        return responses;
    }

    @PostMapping("/search")
    public FriendMemberResponse searchMember(@Validated @RequestBody HashMap<String, String> planFriend){

        log.info("searchMember() :" + planFriend.get("friendId"));

        String id = planFriend.get("friendId");
        return planService.searchMember(id);
    }

    @PostMapping("/addFriend")
    public void planFriend(@Validated @RequestBody PlanFriend planFriend){

        log.info("planFriend() :" + planFriend);

        planService.addFriend(planFriend);
    }

    @PostMapping("/friendList/{planNo}")
    public List<MemberListResponse> friendList(@PathVariable("planNo") Integer planNo){

        log.info("planNo : "+ planNo);

        List<MemberPlan> inviteMember = planService.showInviteMember(planNo);
        List<MemberListResponse> responses = new ArrayList<>();

        for (MemberPlan memberPlan : inviteMember){
            log.info("memberList: " + memberPlan.getMember().getName() + memberPlan.getMember().getId(), memberPlan.getMember().getColor());

            responses.add(new MemberListResponse(memberPlan.getMember().getId(), memberPlan.getMember().getName(), memberPlan.getMember().getColor()));
        }
        return responses;
    }

    @PostMapping("/vote")
    public void voteSave(@Validated @RequestBody VoteRequest voteRequest){
        log.info("voteSave() " + voteRequest.getVoteContent());

        planService.voteSave(voteRequest);
    }

    @GetMapping("/vote/{planNo}")
    public List<VoteResponse> voteList(@PathVariable("planNo") Integer planNo){
        log.info("voteList() " + planNo);

        List<Vote> voteList = planService.voteList(planNo);
        List<VoteResponse> voteResponses = new ArrayList<>();

        for(Vote vote : voteList){
            log.info("voteNo() : "+vote.getVoteNo() + ", " + vote.getVoteContent());

            voteResponses.add(new VoteResponse(vote.getVoteNo(), vote.getVoteContent(), vote.getAgreement(), vote.getOpposition()));
        }

        return voteResponses;
    }

    @PostMapping("/voteGood")
    public void voteGood(@Validated @RequestBody DecisionMakingRequest decisionMakingRequest){
        log.info("voteGood(): "+decisionMakingRequest.getVoteNo());

        planService.voteGood(decisionMakingRequest);
    }

    @PostMapping("/voteBad")
    public void voteBad(@Validated @RequestBody DecisionMakingRequest decisionMakingRequest){
        log.info("voteBad(): "+decisionMakingRequest.getVoteNo());

        planService.voteBad(decisionMakingRequest);
    }
}
