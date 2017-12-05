package org.sdrc.repository;

import java.util.List;

import org.sdrc.domain.User;
import org.sdrc.domain.UserAreaMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAreaMappingRepository extends JpaRepository<UserAreaMapping, Long> {

	List<UserAreaMapping> findByUser(User user);

}
