package uni.social.app.service;

import uni.social.app.dto.RegistrationForm;
import uni.social.app.exception.DuplicateEmailException;
import uni.social.app.model.User;

public interface UserService {

	public User registerNewUserAccount(RegistrationForm userAccountData) throws  DuplicateEmailException;
}