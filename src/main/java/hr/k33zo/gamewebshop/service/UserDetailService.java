package hr.k33zo.gamewebshop.service;

import hr.k33zo.gamewebshop.model.User;
import hr.k33zo.gamewebshop.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {

    private final UserRepo userRepo;

    @Autowired
    public UserDetailService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepo.findById(id);
    }

    public void deleteUserById(Long id) {
        userRepo.deleteById(id);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.singletonList(authority));
    }

    public String getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }


    public long GetUsersIdByName() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long id = userRepo.findByUsername(authentication.getName()).get().getId();
        return id;
    }

    public static Boolean isAdmin(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList()
                .contains("ADMIN");

    }

    public User saveUser(User user) {
        return userRepo.save(user);
    }

    public void deleteUser(Long userId) {
        userRepo.deleteById(userId);
    }

    public void updateUser(User user) {
        userRepo.save(user);
    }

    public String getRole() {

        String role = "";
        String name = getUserName();

        if (userRepo.findByUsername(name).isPresent()) {
            role = userRepo.findByUsername(name).get().getRole();
        }
        return role;
    }

    public long GetUserIdByProvidedName(String name) {

        return userRepo.findByUsername(name).get().getId();
    }
}
