package pl.strefainformacji.component;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import pl.strefainformacji.model.User;

@Getter
public class CurrentUser extends org.springframework.security.core.userdetails.User {
    private User user;

    public CurrentUser(String username, String password, java.util.Collection<?
            extends GrantedAuthority> authorities, pl.strefainformacji.model.User user) {

        super(username, password, authorities);
        this.user = user;
    }
}
