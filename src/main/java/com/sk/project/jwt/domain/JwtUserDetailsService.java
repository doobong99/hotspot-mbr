package com.sk.project.jwt.domain;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sk.project.member.domain.account.Account;
import com.sk.project.member.domain.account.AccountRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
    AccountRepository accountRepository;
	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		if ("javainuse".equals(username)) {
//			return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
//					new ArrayList<>());
//		} else {
//			throw new UsernameNotFoundException("User not found with username: " + username);
//		}
//	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println("acocount");
		
		Account account = accountRepository.findByUsername(username);
		System.out.println("-- "+account.getUsername() + account.getPassword());
		
		String password = null;
		try {
			if(account.getPassword().contains("{bcrypt}")) {
				password = account.getPassword().split("}")[1];
			}
			else {
				password = account.getPassword();
			}
			
//			String password = account.getPassword().split("}")[0];
		
			System.out.println("password"+password);
		}
		catch(Exception e) {
			System.out.println("e "+e.getStackTrace());
		}
		System.out.println("-----");
		
		if (account.getUsername().equals(username)) {
			return new User(account.getUsername(), password,
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}

		
//        if (account == null) {
//            throw new UsernameNotFoundException(username);
//        }
//        
//        
//
//        return User.builder()
//                .username(account.getUsername())
//                .password(account.getPassword())
//                .roles(account.getRole())
//                .build();
		
	}
	
}
