package com.htpindan.modules.test.mail;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import com.htpindan.modules.test.spring.SpringContextTestCase;

import com.icegreen.greenmail.util.GreenMail;

@ContextConfiguration(locations = { "/applicationContext-mail.xml" })
public class MailServerSimulatorTest extends SpringContextTestCase {

	@Autowired
	private GreenMail greenMail;

	@Test
	public void greenMail() {
		assertThat(greenMail.getSmtp().getPort()).isEqualTo(3025);
	}
}
