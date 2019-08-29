package com.sk.project;



import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

//@Slf4j
//@RestController
//@SpringBootApplication
public class SessionTest {
	/*@GetMapping("/request")
    public String getCookie(HttpSession session) {
		System.out.println("-----start------");
        String sessionKey = session.getId();
        session.setAttribute("ID", "yeoseong_yoon");
//        log.info("set userId = {}","yeoseong_yoon");
        
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders header = new HttpHeaders();
//        header.add("Cookie", "SESSION="+redisSessionId);
        HttpEntity<String> requestEntity = new HttpEntity<>(null, header);
        
//        ResponseEntity<String> cookieValue = restTemplate.exchange("http://localhost:8090/request",HttpMethod.GET ,requestEntity ,String.class);
        System.out.println("-----end------");
        return "server1_sessionKey : "+session.getId()+"<br>server2_sessionKey : ";
    }
*/

}
