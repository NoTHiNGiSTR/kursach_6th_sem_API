//package org.mirea.kursachapi.store.entities;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.springframework.data.annotation.Id;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Set;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@Entity
//@Table(name = "users")
//public class User implements UserDetails {
//    @Getter
//    @Setter
//    @jakarta.persistence.Id
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @Column(name = "uuid", nullable = false)
//    String uuid;
//
//    @Column(name = "username", nullable = false, unique = true)
//    String username;
//
//    @Column(name = "password", nullable = false)
//    String password;
//
//    @Column(name = "email", nullable = false)
//    String email;
//
//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @Column(name = "authorities")
//    Set<Authority> authorities;
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//}