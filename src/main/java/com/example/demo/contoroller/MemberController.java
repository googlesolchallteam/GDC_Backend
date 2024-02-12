package com.example.demo.contoroller;

import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @GetMapping("/check")
    public ResponseEntity<Boolean> isMember(@RequestParam(name = "id") String serverId) {
//        System.out.println(serverId);
        Boolean bool = memberService.isMember(Long.parseLong(serverId));
        return new ResponseEntity<>(bool, HttpStatus.OK);
    }

}
