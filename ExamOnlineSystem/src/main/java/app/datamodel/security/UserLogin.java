package app.datamodel.security;

public class UserLogin {
    private String username;
    private Object details;
    private Boolean authenticated;
    private Integer id;
    private Boolean enabled;
    private Boolean credentialsNonExpired;
    private Boolean locked;
    private Authority[] authorities;
    private long timeout;

    public long getTimeout() {
        return timeout;
    }

    public Authority[] getAuthorities() {
        return authorities;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public void setAuthorities(Authority[] authorities) {
        this.authorities = authorities;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Object getDetails() {
        return details;
    }

    public void setDetails(Object details) {
        this.details = details;
    }

    public Boolean getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }
}
