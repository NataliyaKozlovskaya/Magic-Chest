package com.magicchestcore.servicies;

import com.magicchestcore.models.Person;
import com.magicchestcore.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PersonDetailsService implements UserDetailsService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonDetailsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findByUsername(username);
        System.out.println("Ищет");
        if (person == null) {
            throw new UsernameNotFoundException("Person not found");
        }
        return person;
//        return User.builder()
//                .username(person.getUsername())
//                .password(person.getPassword())
//                .authorities(person.getRole().name())
//                .accountLocked(!person.isAccountNonLocked())
//                .build();
    }

}
