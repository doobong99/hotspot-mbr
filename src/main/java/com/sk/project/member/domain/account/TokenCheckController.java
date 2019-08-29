package com.sk.project.member.domain.account;

import org.apache.tomcat.util.json.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sk.project.jwt.JwtTokenUtil;
import com.sk.project.jwt.domain.JwtUserDetailsService;


//@Controller
@RestController
@RequestMapping("/token/chk")
public class TokenCheckController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(method = RequestMethod.POST)
	public Boolean createAuthenticationToken(@RequestBody JSONObject token) throws Exception {
		String token_key = (String) token.get("token");
        System.out.println("token_key "+token_key);
		Boolean token_chk = jwtTokenUtil.isTokenExpired(token_key);
		System.out.println("token_chk : "+token_chk);
		return token_chk;
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
    
}
