package seung.shopping.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import seung.shopping.backend.entity.Member;
import seung.shopping.backend.repository.MemberRepository;

import java.util.Map;


@RequiredArgsConstructor
@RestController
public class AccountController {

    MemberRepository memberRepository;

    @PostMapping("/api/account/login")
    public int login(@RequestBody Map<String, String> params) {
        Member member = memberRepository.findByEmailAndPassword(params.get("email"), params.get("password"));

        if(member != null) {
            return member.getId();
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

}
