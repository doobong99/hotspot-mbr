package com.sk.project.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sk.project.member.domain.account.AccountService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	@Autowired
	private UserDetailsService jwtUserDetailsService;
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
    @Autowired
    AccountService accountService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
// configure AuthenticationManager so that it knows from where to load
// user for matching credentials
// Use BCryptPasswordEncoder
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

/*	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
// We don't need CSRF for this example
		httpSecurity.csrf().disable()
// Don authenticate this particular request
				.authorizeRequests().antMatchers("/hello11","/authenticate","/account/**","/swagger-ui.html","/", "/info","/login","/signup").permitAll().
				mvcMatchers("/admin").hasRole("ADMIN").
// all other requests need to be authenticated
				anyRequest().authenticated().and().
// make sure we use stateless session; session won't be used to
// store user's state.
				exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		httpSecurity.formLogin();
		httpSecurity.httpBasic();
// Add a filter to validate the tokens with every request
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		
	}
	
*/
	
	//@Autowired private RestAuthenticationEntryPoint authenticationEntryPoint;
	
	
	
	/*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .csrf().disable() // We don't need CSRF for JWT based authentication
        .exceptionHandling()
        //.authenticationEntryPoint(this.authenticationEntryPoint)
        
        .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        .and()
            .authorizeRequests()
                .antMatchers("/hello11","/authenticate","/account/**","/swagger-ui.html","/", "/info","/login","/signup").permitAll() // Login end-point
                .antMatchers("/console").permitAll() // H2 Console Dash-board - only for testing
        .and()
            .authorizeRequests()
                .antMatchers("/admin").authenticated(); // Protected API End-points
//        .and()
//            .addFilterBefore(buildAjaxLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
//            .addFilterBefore(buildJwtTokenAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
    }*/
	
	
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/authenticate").permitAll();
		
		http.authorizeRequests()
        .mvcMatchers("/", "/info", "/account/**", "/signup","/authenticate","/login/rest","/token/chk").permitAll()
        .mvcMatchers("/admin").hasRole("ADMIN")
        .mvcMatchers("/user").hasRole("USER")
        .anyRequest().authenticated();
//        .expressionHandler(expressionHandler());

		http.formLogin()
		        .loginPage("/login")
		        .permitAll();
		
		http.rememberMe()
		        .userDetailsService(jwtUserDetailsService)
		        .key("remember-me-sample");
		
		http.httpBasic();
		
		http.logout()
		        .logoutSuccessUrl("/");
    }
	
	
}