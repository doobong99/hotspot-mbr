package com.sk.project.member.domain.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sk.project.jwt.JwtRequest;
import com.sk.project.jwt.JwtTokenUtil;
import com.sk.project.jwt.domain.JwtUserDetailsService;

@Controller
//@RestController
@RequestMapping("/login")
public class LogInAcoountController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private JwtUserDetailsService userDetailsService;
	
    @GetMapping
    public String loginForm() {
    	System.out.println("login start");
        return "login";
    }
    
    @PostMapping
    public String processLogin(@ModelAttribute Account account) {
    	System.out.println("processSignUp");
    	System.out.println("account L "+account.getUsername());
    	
//    	JwtRequest authenticationRequest = null;
//    	authenticationRequest.setUsername(account.getUsername());
//    	authenticationRequest.setPassword(account.getPassword());
    	final UserDetails userDetails = userDetailsService.loadUserByUsername(account.getUsername());
    	final String token = jwtTokenUtil.generateToken(userDetails);
    	
    	System.out.println("token L "+token);
    	
//        account.setRole("USER");
//        accountService.createNew(account);
        return "redirect:/";
    }

    public String createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return token;
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
