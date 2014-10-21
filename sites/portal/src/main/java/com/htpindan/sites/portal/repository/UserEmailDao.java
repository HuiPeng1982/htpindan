package com.htpindan.sites.portal.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.htpindan.sites.portal.entity.User;
import com.htpindan.sites.portal.entity.UserEmail;

public interface UserEmailDao extends
		PagingAndSortingRepository<UserEmail, Long> {
	UserEmail findByEmail(String email);
	
	UserEmail findByEmailAndValidated(String email, boolean validated);

	List<UserEmail> findByUser(User user);
}
