package com.springboot.api.controller;

import com.springboot.api.dto.MemberDTO;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/put-api")
public class PutController {
    @PutMapping(value="/member")
    public String postMember(@RequestBody Map<String, Object> postData){
        StringBuilder sb = new StringBuilder();

        postData.entrySet().forEach(map->{
            sb.append(map.getKey()+":"+map.getValue()+"\n");
        });

        return sb.toString();
    }

    @PutMapping(value="/member1")
    public String postMemberDTO1(@RequestBody MemberDTO memberDTO){
        return memberDTO.toString();
    }

    @PutMapping(value="/member2")
    public MemberDTO postMemberDTO2(@RequestBody MemberDTO memberDTO){
        return memberDTO;
    }

}
