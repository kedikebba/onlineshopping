package edu.miu.pm.onlineshopping.security;

import edu.miu.pm.onlineshopping.admin.model.*;
import edu.miu.pm.onlineshopping.admin.service.ClientService;
import edu.miu.pm.onlineshopping.admin.service.EndUserService;
import edu.miu.pm.onlineshopping.admin.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountDetails implements UserDetails {
    @Autowired
    EndUserService endUserService;
    @Autowired
    VendorService vendorService;
    @Autowired
    ClientService clientService;

    private long id;
    private String email;
    private String password;
    private boolean active;
    private String role;

    public AccountDetails(Account account) {
//        this.firstName=user.getFirstName();
//        this.lastName=user.getLastName();
        this.id = account.getAccountId();
        this.email = account.getEmail();
        this.password = account.getPassword();
//        this.active=user.isActive();
//        this.role=user.getRole();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<String> roles = new HashSet<>();
        EndUser endUser = endUserService.getEndUserByEmail(email);
        Vendor vendor = vendorService.getVendorByEmail(email);
        Client admin = clientService.getAdminByEmail(email);
        if (endUser != null){
            roles.add("ENDUSER");
        } else if (vendor != null){
            roles.add("VENDOR");
        } else if (admin != null){
            roles.add("ADMIN");
        }

        return roles.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toSet());
    }

    public String getRoles() {
        return role;
    }

    public long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }
}
