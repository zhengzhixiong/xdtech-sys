import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.xdtech.common.utils.ClassUtil;
import com.xdtech.core.init.SysInitOperation;

/*
 * Copyright 2013-2014 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * 
 * @author max.zheng
 * @create 2014-9-25下午9:43:47
 * @since 1.0
 * @see
 */
public class TestInit {

	/**
	 * 
	 * @author max.zheng
	 * @create 2014-9-25下午9:43:47
	 * @modified by
	 * @param args
	 */
	public static void main(String[] args) {
		Object initDataEntity = null;
		Method convertMethod = null;
		for (Class<?> c : ClassUtil.getClasses("com.xdtech")) {
//			System.out.println(c.getName());
			if (SysInitOperation.class.isAssignableFrom(c)&&!SysInitOperation.class.equals(c)) {
				System.out.println(c.getName());
				//进行排序
				try {
					initDataEntity = c.newInstance();
					convertMethod = c.getMethod(SysInitOperation.INIT_METHOD);
					convertMethod.invoke(initDataEntity);
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		}
		
//		try {
//			System.out.println("接口实现类：");
//			for (Class<?> c : ClassUtil.getAllAssignedClass(SysInitOperation.class)) {
//				System.out.println(c.getName());
//			}
//			System.out.println("子类：");
//			for (Class<?> c : ClassUtil.getAllAssignedClass(SysInitOperation.class)) {
//				System.out.println(c.getName());
//			}
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

}
