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
package com.xdtech.shop.util;

import org.apache.commons.lang.RandomStringUtils;

/**
 * 
 * @author max.zheng
 * @create 2014-12-8下午9:50:26
 * @since 1.0
 * @see
 */
public class NameUtil {
	
	public static String getRandomImageName() {
		String imageName = System.currentTimeMillis()+RandomStringUtils.random(4, "abcdefghijklmnopqrstuvwxyz");
		return imageName;
	}
}
