package com.furkancetiner.ws.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.furkancetiner.ws.shared.Views;
import com.furkancetiner.ws.utils.UniqueUsername;

import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
public class User implements UserDetails{

	@Id
	@GeneratedValue
	private long id;
	
	@NotNull(message = "{ws.constraint.username.NotNull.message}")
	@Size(min=4,max=255)
	@UniqueUsername
	@JsonView(Views.Base.class)
	private String username;
	
	@NotNull(message = "{ws.constraint.displayname.NotNull.message}")
	@Size(min=4,max=255)
	@JsonView(Views.Base.class)
	private String displayName;
	
	@NotNull(message = "{ws.constraint.password.NotNull.message}")
	@Size(min=8,max=17)
	@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*\\d).*$", message="{ws.constraint.password.Pattern.message}")
	@JsonView(Views.Sensetive.class)
	private String password;
	
	@JsonView(Views.Base.class)
	private String image;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return AuthorityUtils.createAuthorityList("Role_user");
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
