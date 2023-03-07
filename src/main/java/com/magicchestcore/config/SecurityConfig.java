package com.magicchestcore.config;

import com.magicchestcore.config.util.Guard;
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
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static javax.management.Query.and;
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
    protected  void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()// сначаа админ потом продукт
                .authorizeRequests()
                        .antMatchers("/person","/person/admin/{id}",
                                "/order", "/order/{id}","/person/{personId}/order", "/order/{id}",
                                "/admin/**",
                                "/dressModel/admin/**", "/dressSize/admin/**",
                                "/color/admin/**", "/product/admin").hasAuthority("ADMIN")

                        .antMatchers("/person/registration").permitAll()
//                        .antMatchers(HttpMethod.GET,"/person/{id}").access("@guard.checkUserId(authentication,#id)")
//                        .antMatchers(HttpMethod.PATCH,"/person/{id}").access("@guard.checkUserId(authentication,#id)")
//                        .antMatchers(HttpMethod.GET,"/person/{personId}/order").access("@guard.checkUserId(authentication,#id)")
//                        .antMatchers(HttpMethod.GET,"/order/{id}").access("@guard.checkUserId(authentication,#id)")
//                        .antMatchers(HttpMethod.DELETE,"/{id}").access("@guard.checkUserId(authentication,#id)")


                        .anyRequest().authenticated()
                .and()
                .httpBasic(withDefaults());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personDetailsService).passwordEncoder(getPasswordEncoder());
    }
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();//будет заниматься шифрованием
        //return NoOpPasswordEncoder.getInstance();
    }

    @Bean// для чего нужен ???
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers("/person").permitAll()
//                        .anyRequest().hasAnyRole("USER", "ADMIN")
//                )
//                .httpBasic();
//
//        return http.build();
//    }

//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return authenticationManagerBean();//super не ставила
//    }
//}
