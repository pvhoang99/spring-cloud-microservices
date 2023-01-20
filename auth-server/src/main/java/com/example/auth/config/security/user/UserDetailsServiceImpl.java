package com.example.auth.config.security.user;

import com.example.auth.domain.Role;
import com.example.auth.domain.User;
import com.example.auth.repository.RoleRepository;
import com.example.auth.repository.UserRepository;
import java.util.Objects;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Primary
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Setter(onMethod = @__({@Autowired}))
    private UserRepository userRepository;

    @Setter(onMethod = @__({@Autowired}))
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getByUsername(username);
        Role role = null;
        if (!Objects.isNull(user.getRoleId())) {
            role = roleRepository.getOne(user.getRoleId());
        }

        return new UserDetailsImpl(user, role);
    }

}
