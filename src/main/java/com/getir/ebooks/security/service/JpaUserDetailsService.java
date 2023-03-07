package com.getir.ebooks.security.service;

import com.getir.ebooks.security.entity.Authority;
import com.getir.ebooks.security.entity.User;
import com.getir.ebooks.security.entity.enums.EncryptionAlgorithm;
import com.getir.ebooks.security.repository.AuthorityRepository;
import com.getir.ebooks.security.repository.UserRepository;
import com.getir.ebooks.security.model.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    public JpaUserDetailsService(UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) {
        Supplier<UsernameNotFoundException> s =
                () -> new UsernameNotFoundException("Problem during authentication!");

        User u = userRepository.findUserByUsername(username).orElseThrow(s);

        return new CustomUserDetails(u);
    }

    public User save() {

         User user = new User();
         user.setId(1);
         user.setUsername("sema");
         user.setAlgorithm(EncryptionAlgorithm.BCRYPT);
         user.setPassword("$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG");
         userRepository.saveAndFlush(user);

         Authority authorityRead = new Authority();
         authorityRead.setId(1);
         authorityRead.setUser(user);
         authorityRead.setName("READ");
         authorityRepository.saveAndFlush(authorityRead);

        Authority authorityWrite = new Authority();
        authorityWrite.setId(2);
        authorityWrite.setUser(user);
        authorityWrite.setName("WRITE");
        authorityRepository.saveAndFlush(authorityWrite);
        return  user;
    }
}
