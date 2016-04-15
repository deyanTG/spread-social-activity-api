package uni.social.app.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uni.social.app.model.User;

@Repository
@Qualifier("userRepo")
public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String email);
}
