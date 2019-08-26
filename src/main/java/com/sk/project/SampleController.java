package com.sk.project;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class SampleController {

	@GetMapping("/")
	public String index(Model model, Principal principal) {
		System.out.println("principal : " +principal );
		System.out.println("principal name: " +principal.getName() );
		if( principal == null ) {
			model.addAttribute("message", "Hello Spring Security");
		} else {
			model.addAttribute("message", "Hello, "+principal.getName());
		}
		return "index";
	}

	@GetMapping("/info")
	public String info(Model model) {
		model.addAttribute("message", "Info");
		return "info";
	}

	@GetMapping("/dashboard")
	public String dashboard(Model model, Principal principal) {
		model.addAttribute("message", "Hello " + principal.getName());
		return "dashboard";
	}

	@GetMapping("/admin")
	public String admin(Model model, Principal principal) {
		model.addAttribute("message", "Hello Admin, " + principal.getName());
		return "admin";
	}
	
//	@GetMapping("/request")
//    public String getCookie(HttpSession session) {
//        String sessionKey = session.getId();
//        session.setAttribute("ID", "yeoseong_yoon");
//        System.out.println("set userId = {} " + session.getAttribute(sessionKey));
//        
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders header = new HttpHeaders();
////        header.add("Cookie", "SESSION="+redisSessionId);
//        HttpEntity<String> requestEntity = new HttpEntity<>(null, header);
//        
//        ResponseEntity<String> cookieValue = restTemplate.exchange("http://localhost:8090/request",HttpMethod.GET ,requestEntity ,String.class);
//        return "server1_sessionKey : "+session.getId()+"<br>server2_sessionKey : "+cookieValue.getBody();
//    }


	
}
