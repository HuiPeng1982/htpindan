package com.htpindan.sites.portal.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.htpindan.sites.portal.entity.Order;

public interface OrderDao extends PagingAndSortingRepository<Order, Long> {
	Order findByOrderNumAndSupplier(String orderNum, String supplier);

}
