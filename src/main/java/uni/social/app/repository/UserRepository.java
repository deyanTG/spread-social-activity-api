package uni.social.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uni.social.app.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String email);
}
