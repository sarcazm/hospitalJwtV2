package poliklinika.security.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import poliklinika.model.Role;
import poliklinika.model.Status;
import poliklinika.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user){
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getRole()),
                user.getStatus().equals(Status.ACTIVE)
                );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(Role role) {
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        return roles.stream()
                .map(r ->
                        new SimpleGrantedAuthority(r.toString())
                ).collect(Collectors.toList());
    }
}
