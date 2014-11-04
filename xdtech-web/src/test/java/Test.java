import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xdtech.core.config.PropertiesConfigurer;


public class Test {
	public static void main(String[] args) {
//		org.springframework.web.util.Log4jConfigListener
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//		System.out.println(ac.getBean("baseDao"));
//		System.out.println(PropertiesConfigurer.getValue("hibernate.dialect"));
		System.out.println(ac.getBean("commonController"));
	}
}
