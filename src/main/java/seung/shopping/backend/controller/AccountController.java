package seung.shopping.backend.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import seung.shopping.backend.entity.Member;
import seung.shopping.backend.repository.MemberRepository;
import seung.shopping.backend.service.JwtService;
import seung.shopping.backend.service.JwtServiceImpl;

import java.util.Map;


@RequiredArgsConstructor
@RestController
public class AccountController {

    MemberRepository memberRepository;

    @PostMapping("/api/account/login")
    public ResponseEntity login(@RequestBody Map<String, String> params,
        HttpServletResponse res) {
        Member member = memberRepository.findByEmailAndPassword(params.get("email"), params.get("password"));

        // 멤버값이 널이 아닐경우
        if(member != null) {
            JwtService jwtService = new JwtServiceImpl();
            int id = member.getId();
            // id 토큰화
            String token = jwtService.getToken("id", id);
            // 토큰을 쿠키에 추가
            Cookie cookie = new Cookie("token", token);
            cookie.setHttpOnly(true);
            cookie.setPath("/");

            res.addCookie(cookie);
            // 쿠키를 응답값에 준다 f12 쿠키로 확인가능
            // return ResponseEntity.ok().build();
            // 응답값 오는지 확인가능
            return new ResponseEntity<>(id, HttpStatus.OK);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

}
