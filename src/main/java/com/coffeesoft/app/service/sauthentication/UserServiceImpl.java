package com.coffeesoft.app.service.sauthentication;

import com.coffeesoft.app.model.entity.Account;
import com.coffeesoft.app.model.entity.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final EntityManager entityManager;

    public UserServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Account findByUserName(String username) {

        final String HQL = "FROM Account WHERE username =:username";

        TypedQuery<Account> query = entityManager.createQuery(HQL, Account.class);
        query.setParameter("username", username);

        Account account = null;

        try {

            account = query.getSingleResult();

        } catch (RuntimeException ex) {
            System.out.println("Not found");
        }
        return account;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = findByUserName(username);

        if (account == null) {
            throw new UsernameNotFoundException("Invalid username or Password");
        }

        return new User(account.getUsername(), account.getPassword(),
                                    mapRolesToAuthorities(List.of(account.getRole())));

    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {

        return roles.stream().map(
                role -> new SimpleGrantedAuthority(role.getRoleName()))
                        .collect(Collectors.toList());
    }
}
