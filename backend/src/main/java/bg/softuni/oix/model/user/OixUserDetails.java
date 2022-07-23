package bg.softuni.oix.model.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class OixUserDetails implements UserDetails {
    private Long id;
    private String password;
    private String username;
    private String firstName;
    private String lastName;
    private final Collection<GrantedAuthority> authorities;

    public OixUserDetails(Long id, String password, String username, String firstName, String lastName, Collection<GrantedAuthority> authorities) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.authorities = authorities;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName(){
        StringBuilder sb = new StringBuilder();
        if (getFirstName() != null){
            sb.append(getFirstName());
        }
        if (getLastName() != null){
            if (sb.length() != 0){
                sb.append(" ");
            }
            sb.append(getLastName());
        }
        return sb.toString();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return true;
    }
}
