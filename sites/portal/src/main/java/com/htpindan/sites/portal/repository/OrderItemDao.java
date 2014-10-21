package com.htpindan.sites.portal.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.htpindan.sites.portal.entity.OrderItem;

public interface OrderItemDao extends PagingAndSortingRepository<OrderItem, Long> {

}
