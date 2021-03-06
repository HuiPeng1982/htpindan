package com.htpindan.modules.memcached;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import com.htpindan.modules.cache.memcached.SpyMemcachedClient;
import com.htpindan.modules.test.category.UnStable;
import com.htpindan.modules.test.spring.SpringContextTestCase;
import com.htpindan.modules.utils.Threads;

import com.google.common.collect.Lists;

@Category(UnStable.class)
@ContextConfiguration(locations = { "/applicationContext-memcached.xml" })
public class SpyMemcachedClientTest extends SpringContextTestCase {

	@Autowired
	private SpyMemcachedClient client;

	@Test
	public void normal() {

		String key = "consumer:1";
		String value = "admin";

		String key2 = "consumer:2";
		String value2 = "user";

		// get/set
		client.set(key, 60 * 60 * 1, value);
		Threads.sleep(1000);
		String result = client.get(key);
		assertThat(result).isEqualTo(value);

		// safeSet
		client.safeSet(key2, 60 * 60 * 1, value2);
		result = client.get(key2);
		assertThat(result).isEqualTo(value2);

		// bulk
		Map<String, Object> bulkResult = client.getBulk(Lists.newArrayList(key, key2));
		assertThat(bulkResult).containsOnly(entry(key, value), entry(key2, value2));

		// delete
		client.delete(key);
		Threads.sleep(1000);
		result = client.get(key);
		assertThat(result).isNull();

		client.safeDelete(key);
		result = client.get(key);
		assertThat(result).isNull();
	}

	@Test
	public void incr() {
		String key = "counter";

		assertThat(client.incr(key, 1, 1)).isEqualTo(1);
		// 注意counter的实际类型是String
		assertThat(client.get(key)).isEqualTo("1");

		assertThat(client.incr(key, 1, 1)).isEqualTo(2);
		assertThat(client.get(key)).isEqualTo("2");

		assertThat(client.decr(key, 2, 1)).isEqualTo(0);
		assertThat(client.get(key)).isEqualTo("0");
	}
}
