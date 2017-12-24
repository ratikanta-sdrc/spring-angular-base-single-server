package org.sdrc.repository;

import java.util.Optional;

import org.sdrc.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * @author ratikanta@sdrc.co.in
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUserName(String username);	

}
