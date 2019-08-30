package com.sk.project.member.domain.account;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AccountService implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException(username);
        }

        return User.builder()
                .username(account.getUsername())
                .password(account.getPassword())
                .roles(account.getRole())
                .build();
    }
    
    public Account createNew (Account account) {
    	account.encodePassword(passwordEncoder);
    	return this.accountRepository.save(account);
    	//account.setPassword("{noop}" + account.getPassword());
    	//return account;
    }

	public Account findId(Account account) {
		// TODO Auto-generated method stub
		return accountRepository.findByUsername(account.getUsername());
	}

	public Account updateAccount(Account account) {
		// TODO Auto-generated method stub
//		return accountRepository.
		Account oldAccount = accountRepository.findByUsername(account.getUsername());
		
		
		Account newAccount = new Account(
				oldAccount.getId(),
				oldAccount.getUsername(),
				oldAccount.getPassword(),
				oldAccount.getRole()
				);
		
		return accountRepository.save(newAccount);
//		return null;
	}
	
	
//	public Review updateReview(Long reviewId, ReviewDto reviewDto) {
//        Optional<Review> oldReview = reviewRepository.findById(reviewId);
//        if (oldReview.isPresent()) {
//            Review newReview = new Review(
//                    reviewDto.getCustomerId(),
//                    reviewDto.getStoreId(),
//                    reviewDto.getContent(),
//                    null,
//                    new Date());
//            BeanUtils.copyProperties(newReview, oldReview.get(), "id", "registDate");
//            return reviewRepository.save(oldReview.get());
//        } else {
//            return null;
//        }
//    }
	
}
