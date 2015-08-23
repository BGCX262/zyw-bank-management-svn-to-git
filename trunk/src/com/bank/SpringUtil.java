package com.bank;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtil {
	public static Object getBean(String beanName) {
		ApplicationContext context = 
			new ClassPathXmlApplicationContext("/ApplicationContext.xml");
		return context.getBean(beanName);
	}
}
