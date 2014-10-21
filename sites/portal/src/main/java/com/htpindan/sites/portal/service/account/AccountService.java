package com.htpindan.sites.portal.service.account;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.htpindan.modules.security.utils.Digests;
import com.htpindan.modules.utils.Clock;
import com.htpindan.modules.utils.Encodes;
import com.htpindan.sites.portal.entity.User;
import com.htpindan.sites.portal.entity.UserEmail;
import com.htpindan.sites.portal.repository.UserDao;
import com.htpindan.sites.portal.repository.UserEmailDao;
import com.htpindan.sites.portal.service.ServiceException;
import com.htpindan.sites.portal.service.account.ShiroDbRealm.ShiroUser;

/**
 * 用户管理类.
 * 
 */
// Spring Service Bean的标识.
@Component
@Transactional
public class AccountService {

	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;
	private static final int EMAIL_SALT_SIZE = 16;

	private static Logger logger = LoggerFactory
			.getLogger(AccountService.class);

	private UserDao userDao;
	private UserEmailDao userEmailDao;
	private Clock clock = Clock.DEFAULT;

	public List<User> getAllUser() {
		return (List<User>) userDao.findAll();
	}

	public User getUser(Long id) {
		return userDao.findOne(id);
	}

	public User findUserByName(String name) {
		return userDao.findByName(name);
	}

	public User findUserByEmail(String email) {
		return userDao.findByEmail(email);
	}

	public UserEmail findUserEmailByEmail(String email) {
		return userEmailDao.findByEmail(email);
	}
	
	public boolean checkNameAndEmail(User user){
		if (findUserByName(user.getName()) != null){
			return false;
		}else if(findUserByEmail(user.getEmail()) != null){
			return false;
		}else if(findUserEmailByEmail(user.getEmail()) != null){
			return false;
		}
		return true;
	}

	public void registerUser(User user) {
		if (findUserByName(user.getName()) != null){
			throw new ServiceException("名字已被别人注册，请重试.");
		}else if(findUserByEmail(user.getEmail()) != null || findUserEmailByEmail(user.getEmail()) != null){
			throw new ServiceException("电邮已被别人已注册，请重试.");
		}
		entryptPassword(user);
		user.setRoles("user");
		user.setRegisterDate(clock.getCurrentDate());

		userDao.save(user);

		addUserEmail(user, user.getEmail());
	}

	public void addUserEmail(User user, String email) {
		UserEmail ue = new UserEmail();
		ue.setEmail(email);
		ue.setUser(user);
		ue.setValidated(false);
		byte[] salt = Digests.generateSalt(EMAIL_SALT_SIZE);
		ue.setSalt(Encodes.encodeHex(salt));
		userEmailDao.save(ue);
	}

	public void updateUser(User user) {
		if (StringUtils.isNotBlank(user.getPlainPassword())) {
			entryptPassword(user);
		}
		userDao.save(user);
	}

	public void deleteUser(Long id) {
		if (isSupervisor(id)) {
			logger.warn("操作员{}尝试删除超级管理员用户", getCurrentUserName());
			throw new ServiceException("不能删除超级管理员用户");
		}
		userDao.delete(id);

	}

	/**
	 * 判断是否超级管理员.
	 */
	private boolean isSupervisor(Long id) {
		return id == 1;
	}

	/**
	 * 取出Shiro中的当前用户name.
	 */
	private String getCurrentUserName() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user.name;
	}

	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	private void entryptPassword(User user) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		user.setSalt(Encodes.encodeHex(salt));

		byte[] hashPassword = Digests.sha1(user.getPlainPassword().getBytes(),
				salt, HASH_INTERATIONS);
		user.setPassword(Encodes.encodeHex(hashPassword));
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setUserEmailDao(UserEmailDao userEmailDao) {
		this.userEmailDao = userEmailDao;
	}

	public void setClock(Clock clock) {
		this.clock = clock;
	}
}
