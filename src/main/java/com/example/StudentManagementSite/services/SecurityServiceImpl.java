package com.example.StudentManagementSite.services;

import com.example.StudentManagementSite.entity.User;
import com.example.StudentManagementSite.repository.SecurityRepository;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService,
        UserDetailsService {

    @Autowired
    private SecurityRepository securityRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return securityRepository.findByUsername(username);
    }

    @Override
    public void save(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        securityRepository.save(user);
    }

    @Override
    public void logout() {
        UI.getCurrent().getPage().setLocation("/login");
        var logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(VaadinServletRequest.getCurrent().getHttpServletRequest(), null, null);
    }
}
