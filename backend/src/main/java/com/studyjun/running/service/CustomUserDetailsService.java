package com.studyjun.running.service;

import com.studyjun.running.common.UserPrincipal;
import com.studyjun.running.entity.User;
import com.studyjun.running.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("사용자를 찾을 수 없습니다.")
                );

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new UsernameNotFoundException("해당 ID의 사용자를 찾을 수 없습니다.")
                );
        return UserPrincipal.create(user);
    }
}