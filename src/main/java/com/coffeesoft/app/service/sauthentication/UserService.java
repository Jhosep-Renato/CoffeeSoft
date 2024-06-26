package com.coffeesoft.app.service.sauthentication;

import com.coffeesoft.app.model.entity.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    Account findByUserName(String username);
}
