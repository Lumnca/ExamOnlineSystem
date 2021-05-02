package app.datamodel.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class User  implements UserDetails {
    private Integer _id;
    private String _username;
    private String _password;
    private Boolean _enabled;
    private Boolean _locked;
    private String _no;
    private List<Role> _roles;

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String get_username() {
        return _username;
    }

    public void set_username(String _username) {
        this._username = _username;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public Boolean get_enabled() {
        return _enabled;
    }

    public void set_enabled(Boolean _enabled) {
        this._enabled = _enabled;
    }

    public Boolean get_locked() {
        return _locked;
    }

    public void set_locked(Boolean _locked) {
        this._locked = _locked;
    }

    public List<Role> get_roles() {
        return _roles;
    }

    public void set_roles(List<Role> _roles) {
        this._roles = _roles;
    }

    public String get_no() {
        return _no;
    }

    public void set_no(String _no) {
        this._no = _no;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role:_roles
        ) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return _password;
    }

    @Override
    public String getUsername() {
        return _username;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !_locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return _enabled;
    }
}
