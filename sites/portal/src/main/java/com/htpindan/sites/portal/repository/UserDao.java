package com.htpindan.sites.portal.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.htpindan.sites.portal.entity.User;

public interface UserDao extends PagingAndSortingRepository<User, Long> {
	User findByName(String name);

	User findByEmail(String email);
}
