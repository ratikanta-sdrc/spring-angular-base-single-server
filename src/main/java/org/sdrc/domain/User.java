package org.sdrc.domain;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 
 * @author azaruddin@sdrc.co.in
 * 
 *         In this class don't use any lazy collection mapping if you are
 *         serilizing User object directly into frontend.Because Jackson
 *         requires Hibernate session to load lazy collections.
 * 
 *         If you want to, then try to load all lazy class with EntityGraph
 *
 */

@Entity
@Table(name = "user_tbl")
public class User implements Serializable, UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;

	@Column
	private String name;

	@Column(name = "user_name")
	private String userName;

	private String password;

	@Column(name = "enabled", columnDefinition = "boolean DEFAULT true")
	private boolean enabled;

	@Column(name = "credentialexpired", columnDefinition = "boolean DEFAULT false")
	private boolean credentialexpired;

	@Column(name = "expired", columnDefinition = "boolean DEFAULT false")
	private boolean expired;

	@Column(name = "locked", columnDefinition = "boolean DEFAULT false")
	private boolean locked;

	@Transient
	private Collection<GrantedAuthority> authorities;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isCredentialexpired() {
		return credentialexpired;
	}

	public void setCredentialexpired(boolean credentialexpired) {
		this.credentialexpired = credentialexpired;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !this.expired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !this.locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	public User() {

	}
}
