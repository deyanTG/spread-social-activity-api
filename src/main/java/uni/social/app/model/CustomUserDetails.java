package uni.social.app.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.social.security.SocialUser;

public class CustomUserDetails extends SocialUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8409224690228379474L;

	private Long id;

	private String firstName;

	private String lastName;

	private UserRole role;

	private SocialMediaService socialSignInProvider;

	public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public SocialMediaService getSocialSignInProvider() {
		return socialSignInProvider;
	}

	public void setSocialSignInProvider(SocialMediaService socialSignInProvider) {
		this.socialSignInProvider = socialSignInProvider;
	}

	public static class UserBuilder {

		private Long id;

		private String username;

		private String firstName;

		private String lastName;

		private String password;

		private UserRole role;

		private SocialMediaService socialSignInProvider;

		private Set<GrantedAuthority> authorities;

		public UserBuilder() {
			this.authorities = new HashSet<>();
		}

		public UserBuilder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public UserBuilder id(Long id) {
			this.id = id;
			return this;
		}

		public UserBuilder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public UserBuilder password(String password) {
			if (password == null) {
				password = "SocialUser";
			}

			this.password = password;
			return this;
		}

		public UserBuilder role(UserRole role) {
			this.role = role;

			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.toString());
			this.authorities.add(authority);

			return this;
		}

		public UserBuilder socialSignInProvider(SocialMediaService socialSignInProvider) {
			this.socialSignInProvider = socialSignInProvider;
			return this;
		}

		public UserBuilder username(String username) {
			this.username = username;
			return this;
		}

		public CustomUserDetails build() {
			CustomUserDetails user = new CustomUserDetails(username, password, authorities);

			user.id = id;
			user.firstName = firstName;
			user.lastName = lastName;
			user.role = role;
			user.socialSignInProvider = socialSignInProvider;

			return user;
		}
	}

}
