package org.sdrc.repository;

import org.sdrc.domain.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	@Cacheable("users")
	User findByUserName(String username);

	

}
