package com.magicchestcore.config;

import com.magicchestcore.servicies.PersonDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final PersonDetailsService personDetailsService;

    @Autowired
    public SecurityConfig(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .authorizeRequests()
                        .antMatchers("/person", "/person/admin/{id}", "/person/admin/lock/{id}",
                                "/person/admin/unlock/{id}", "/product/admin", "/product/admin/{id}",
                                "/order", "/dressSize/admin/**", "/dressModel/admin/**", "/color/admin/**",
                                "/bagSize/admin/**", "/bagModel/admin/**", "/shoesModel/admin/**",
                                "/shoesSize/admin/**", "/promoCode/admin/**").hasAuthority("ADMIN")
                        .antMatchers( "/order/save").hasAuthority("USER")
                        .antMatchers("/person/registration","/person/login").permitAll()
                        .antMatchers(HttpMethod.GET,"order/{personId}").access("@guard.checkUserId(authentication,#id) or hasRole('ROLE_ADMIN')")//?????????
                        .antMatchers(HttpMethod.GET,"order/orderId/{id}").access("@guard.checkUserId(authentication,#id) or hasAnyAuthority('ADMIN')")                       .antMatchers(HttpMethod.PATCH,"/person/{id}").access("@guard.checkUserId(authentication,#id)")
                        .antMatchers(HttpMethod.GET,"/person/orderId/{id}").access("@guard.checkUserId(authentication,#id)")
                        .antMatchers(HttpMethod.DELETE,"order/{id}").access("@guard.checkUserId(authentication,#id)")
                        .antMatchers(HttpMethod.GET,"/promoCode/{personId}").access("@guard.checkUserId(authentication,#id) or hasAnyAuthority('ADMIN')")
                        .anyRequest().authenticated()
                .and()
                .httpBasic(withDefaults());
    }

    @Override // Sets up authentication
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personDetailsService).passwordEncoder(getPasswordEncoder());
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}

