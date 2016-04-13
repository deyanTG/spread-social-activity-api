package uni.social.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import uni.social.app.model.CustomUserDetails;
import uni.social.app.model.User;
import uni.social.app.repository.UserRepository;

public class RepositoryUserDetailsService implements UserDetailsService {

	private UserRepository repository;

	@Autowired
	public RepositoryUserDetailsService(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByEmail(username);

		if (user == null) {
			throw new UsernameNotFoundException("No user found with username: " + username);
		}

		CustomUserDetails.UserBuilder builder = new CustomUserDetails.UserBuilder();

		CustomUserDetails principal = builder.firstName(user.getFirstName()).id(user.getId())
				.lastName(user.getLastName()).password(user.getPassword()).role(user.getRole())
				.socialSignInProvider(user.getSignInProvider()).username(user.getEmail()).build();

		return principal;
	}

}
