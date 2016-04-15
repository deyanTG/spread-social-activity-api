package uni.social.app.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import uni.social.app.model.CustomUserDetails;
import uni.social.app.model.User;

public class SecurityUtil {
	public static void logInUser(User user) {
		CustomUserDetails.UserBuilder builder = new CustomUserDetails.UserBuilder();
		CustomUserDetails userDetails = builder.firstName(user.getFirstName()).id(user.getId())
				.lastName(user.getLastName()).password(user.getPassword()).role(user.getRole())
				.socialSignInProvider(user.getSignInProvider()).username(user.getEmail()).build();

		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
				userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
