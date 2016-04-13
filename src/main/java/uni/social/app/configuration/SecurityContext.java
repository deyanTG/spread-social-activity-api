package uni.social.app.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;

import uni.social.app.repository.UserRepository;
import uni.social.app.service.CustomSocialUserDetailsService;
import uni.social.app.service.RepositoryUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityContext extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/static/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/login").loginProcessingUrl("/login/authenticate")
				.failureUrl("/login?error=bad_credentials").and().logout().deleteCookies("JSESSIONID")
				.logoutUrl("/logout").logoutSuccessUrl("/login").and().authorizeRequests()
				.antMatchers("/auth/**", "/login", "/signup/**", "/user/register/**").permitAll().antMatchers("/**")
				.hasRole("USER").and().apply(new SpringSocialConfigurer());
	}

	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}

	@Bean
	public SocialUserDetailsService socialUserDetailsService() {
		return new CustomSocialUserDetailsService(userDetailsService());
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new RepositoryUserDetailsService(userRepository);
	}

}
