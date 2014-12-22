
package com.xdtech.shop.util;

import java.util.Date;

import com.xdtech.common.utils.DateUtil;

public class NumberUtil
{
	/**
	 * 获取日期序列数，休眠100毫秒是为了防止并发产生相同序列问题
	 * @since 2014-8-26 下午1:48:21
	 * @return
	 */
	public static String getDateSerializable()
	{
		String number = DateUtil.dateToString(new Date(), DateUtil.DateStyle.YYYYMMDDHHMMSSSSS);
		try
		{
			Thread.sleep(100);
		}
		catch (InterruptedException e)
		{
			number = DateUtil.dateToString(new Date(), DateUtil.DateStyle.YYYYMMDDHHMMSSSSS);
		}
		return number;
	}

	

	
	public static void main(String[] args) throws Exception
	{
		while (true)
		{
			System.out.println(getDateSerializable());
//			System.out.println(new TimeStamp(new Date()));
//			System.out.println(DateUtil.dateToString(new Date(), DateUtil.DateStyle.YYYYMMDDHHMMSS));

//			java.util.Date   now=new   java.util.Date();   
//		    java.text.SimpleDateFormat   formatter=new   java.text.SimpleDateFormat("yyyyMMddHHmmssSSS");
//		    System.out.println(formatter.format(now));
//			Thread.sleep(100);
		}
//		System.out.println(DateUtil.dateToString(new Date(), DateUtil.DateStyle.YYYYMMDDHHMMSS));
//		Date date = new Date();
//		dateFlag = DateUtil.dateToString(date, DateUtil.DateStyle.YYYYMMDD);
//		String number = String.format("%tY%<tm%<td%<th%04d", date, index);
//		System.out.println(number);
//	    java.util.Date   now=new   java.util.Date();   
//	    java.text.SimpleDateFormat   formatter=new   java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//	    System.out.print(formatter.format(now));
	}
}
