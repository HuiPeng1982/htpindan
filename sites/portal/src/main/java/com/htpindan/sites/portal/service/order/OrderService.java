package com.htpindan.sites.portal.service.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.htpindan.modules.mapper.JsonMapper;
import com.htpindan.modules.utils.Clock;
import com.htpindan.sites.portal.Currency;
import com.htpindan.sites.portal.OrderState;
import com.htpindan.sites.portal.entity.Item;
import com.htpindan.sites.portal.entity.Order;
import com.htpindan.sites.portal.entity.OrderItem;
import com.htpindan.sites.portal.entity.User;
import com.htpindan.sites.portal.repository.ItemDao;
import com.htpindan.sites.portal.repository.OrderDao;
import com.htpindan.sites.portal.repository.OrderItemDao;
import com.htpindan.sites.portal.repository.UserEmailDao;
import com.htpindan.sites.portal.service.ServiceException;

/**
 * 订单Email登录管理类.
 * 
 */
// Spring Service Bean的标识.
@Component
@Transactional
public class OrderService {
	private static Logger logger = LoggerFactory
			.getLogger(OrderService.class);
	
	private OrderDao orderDao;
	private ItemDao itemDao;
	private OrderItemDao orderItemDao;
	private UserEmailDao userEmailDao;
	private Clock clock = Clock.DEFAULT;
	private static JsonMapper binder = JsonMapper.nonDefaultMapper();
	
	public void fromEmailJson(String emailJson) throws Exception{
		List<Map<String, Object>> mapList = binder.getMapper().readValue(emailJson, List.class);
		for (Map<String, Object> element : mapList) {
			Order order = orderMapper(element);
			if(order == null){
				throw new ServiceException("未知Email Address!");
			}
			Order oriOrder = orderDao.findByOrderNumAndSupplier(order.getOrderNum(), order.getSupplier());
			if(oriOrder == null){
				orderDao.save(order);
				saveOrderItems(order);
			}else if("9".equals(oriOrder.getState())){
				//已完结OrderEmail，暂时不处理
			}else if(! oriOrder.getState().equals(order.getState())){
				if(OrderState.ORDERED.equals(oriOrder.getState())){
					oriOrder.setDeliveryTracking(order.getDeliveryTracking());
					oriOrder.setDeliveryDate(order.getDeliveryDate());
					oriOrder.setState(OrderState.COMPLATE);
					orderDao.save(oriOrder);
					saveOrderItems(oriOrder);
				}else if(OrderState.SHIPED.equals(oriOrder.getState())){
					order.setId(oriOrder.getId());
					order.setDeliveryTracking(oriOrder.getDeliveryTracking());
					order.setDeliveryDate(oriOrder.getDeliveryDate());
					order.setState(OrderState.COMPLATE);
					orderDao.save(order);
					saveOrderItems(order);
				}
			}else{
				//重复提交OrderEmail，暂时不处理
			}
		}
	}
	
	private void saveOrderItems(Order order){
		List<OrderItem> needSaveOrderItems = new ArrayList<OrderItem>();
		if(order.getTransientItems() != null && order.getTransientItems().size() > 0){
			for(Item item : order.getTransientItems()){
				OrderItem oi = new OrderItem();
				Item oriItem = itemDao.findByOriginalNumAndSupplier(item.getOriginalNum(), item.getSupplier());
				if(oriItem == null){
					//TODO price 未处理
					itemDao.save(item);
					oi.setItem(item);
				}else{
					oi.setItem(oriItem);
				}
				oi.setCurrency(getCurrency(item.getPrice()));
				oi.setPrice(getPrice(item.getPrice()));
				oi.setOrder(order);
				oi.setQuantity(item.getQuantity());
				needSaveOrderItems.add(oi);
			}
			orderItemDao.save(needSaveOrderItems);
		}
	}
	
	private Currency getCurrency(String priveString){
		if(priveString.startsWith("$")){
			return Currency.USD;
		}else if(priveString.startsWith("EUR")){
			return Currency.EUR;
		}else if(priveString.startsWith("￥")){
			return Currency.JPY;
		}else{
			throw new ServiceException("未知货币符号!");
		}
	}
	
	private double getPrice(String priveString){
		String p = priveString.replaceAll("$", "").replaceAll("EUR", "").replaceAll("￥", "").replaceAll(",", "").trim();
		return new Double(p).doubleValue();
	}
	
	private Order orderMapper(Map<String, Object> element){
		Order order = new Order();
		List<Item> items;
		for(String key : element.keySet() ){
			if("order_id".equals(key)){
				order.setOrderNum((String) element.get(key));
			}else if("supplier".equals(key)){
				order.setSupplier((String) element.get(key));
			}else if("billing_info".equals(key)){
				order.setOrderInfo((String) element.get(key));
			}else if("state".equals(key)){
				if(((Integer) element.get(key)).intValue() == 1){
					order.setState(OrderState.ORDERED);
				}else if(((Integer) element.get(key)).intValue() == 2){
					order.setState(OrderState.SHIPED);
				}
			}else if("order_date".equals(key)){
				order.setOrderDate((String) element.get(key));
			}else if("delivery_to".equals(key)){
				order.setDeliveryAddress((String) element.get(key));
			}else if("delivery_tracking".equals(key)){
				order.setDeliveryTracking((String) element.get(key));
			}else if("delivery_date".equals(key)){
				order.setDeliveryDate((String) element.get(key));
			}else if("email".equals(key)){
				order.setEmail((String) element.get(key));
				User user = userEmailDao.findByEmailAndValidated(order.getEmail(), true).getUser();
				if(user != null){
					order.setUser(user);
				}else{
					return null;
				}
			}else if("order_items".equals(key)){
				items = itemsMapper((List<Map<String, Object>>) element.get(key), order.getSupplier());
				order.setTransientItems(items);
			}
		}
		return order;
	}
	
	private List<Item> itemsMapper(List<Map<String, Object>> items, String supplier){
		List<Item> itemList = new ArrayList();
		for(Map<String, Object> item :  ((List<Map<String, Object>>) items)){
			Item it = new Item();
			for(String key : item.keySet() ){
				it.setSupplier(supplier);
				if("url".equals(key)){
					it.setUrl((String) item.get(key));
				}else if("original_id".equals(key)){
					it.setOriginalNum((String) item.get(key));
				}else if("img".equals(key)){
					it.setImg((String) item.get(key));
				}else if("name".equals(key)){
					it.setName((String) item.get(key));
				}else if("brand".equals(key)){
					it.setBrand((String) item.get(key));
				}else if("supplier".equals(key)){
					it.setSupplier((String) item.get(key));
				}else if("price".equals(key)){
					it.setPrice((String) item.get(key));
				}else if("quantity".equals(key)){
					it.setQuantity(((Integer) item.get(key)).intValue());
				}
			}
			itemList.add(it);
		}
		return itemList;
	}
	
//	private List<OrderItem> orderItemMapper(List<Item> itemList){
//		List<OrderItem> items = new ArrayList();
//		return items;
//	}
	
	@Autowired
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	@Autowired
	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	@Autowired
	public void setUserEmailDao(UserEmailDao userEmailDao) {
		this.userEmailDao = userEmailDao;
	}

	@Autowired
	public void setOrderItemDao(OrderItemDao orderItemDao) {
		this.orderItemDao = orderItemDao;
	}
	
	

}
