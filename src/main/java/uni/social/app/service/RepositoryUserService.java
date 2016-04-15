package uni.social.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uni.social.app.dto.RegistrationForm;
import uni.social.app.exception.DuplicateEmailException;
import uni.social.app.model.User;
import uni.social.app.repository.UserRepository;

@Service
public class RepositoryUserService implements UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository repository;

	@Transactional
	@Override
	public User registerNewUserAccount(RegistrationForm userAccountData) throws DuplicateEmailException {
		if (emailExist(userAccountData.getEmail())) {
			throw new DuplicateEmailException(
					"The email address: " + userAccountData.getEmail() + " is already in use.");
		}

		String encodedPassword = encodePassword(userAccountData);

		User.Builder user = User.getBuilder().email(userAccountData.getEmail())
				.firstName(userAccountData.getFirstName()).lastName(userAccountData.getLastName())
				.password(encodedPassword);

		if (userAccountData.isSocialSignIn()) {
			user.signInProvider(userAccountData.getSignInProvider());
		}

		User registered = user.build();

		return repository.save(registered);
	}

	private boolean emailExist(String email) {
		User user = repository.findByEmail(email);

		if (user != null) {
			return true;
		}

		return false;
	}

	private String encodePassword(RegistrationForm dto) {
		String encodedPassword = null;

		if (dto.isNormalRegistration()) {
			encodedPassword = passwordEncoder.encode(dto.getPassword());
		}

		return encodedPassword;
	}
}
