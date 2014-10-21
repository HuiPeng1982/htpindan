package com.htpindan.sites.portal.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.htpindan.sites.portal.entity.Item;

public interface ItemDao extends PagingAndSortingRepository<Item, Long> {
	Item findByOriginalNumAndSupplier(String originalNum, String supplier);
}
