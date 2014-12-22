package com.xdtech.shop.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.xdtech.shop.conditions.OrderCondition;
import com.xdtech.shop.service.CategoryService;
import com.xdtech.shop.service.OrderService;
import com.xdtech.shop.util.NameUtil;
import com.xdtech.shop.util.NumberUtil;
import com.xdtech.shop.vo.CategoryItem;
import com.xdtech.shop.vo.OrderItem;
import com.xdtech.show.vo.MemberItem;
import com.xdtech.stu.conditions.StudentCondition;
import com.xdtech.web.model.Pagination;
import com.xdtech.web.model.ResultMessage;

/**
 * 
 * @author max.zheng
 * @create 2014-12-08 20:33:28
 * @since 1.0
 * @see
 */
@Controller
@Scope("prototype")
@RequestMapping("/order.do")
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private JavaMailSender sender;
	@Autowired
	private CategoryService categoryService;

	@RequestMapping(params = "order")
	public ModelAndView skipOrder() {
		return new ModelAndView("shop/order/order_ftl");
	}

	@RequestMapping(params = "loadList")
	@ResponseBody
	public Map<String, Object> loadList(HttpServletRequest request,Pagination pg,OrderItem condition) {
		Map<String, Object> results = orderService.loadPageCondition(pg, condition);
		return results;
	}

	@RequestMapping(params = "editOrder")
	public ModelAndView editOrder(HttpServletRequest request, Long orderId) {
		if (orderId != null) {
			request.setAttribute("orderItem",
					orderService.loadOrderItem(orderId));
		}
		return new ModelAndView("shop/order/editOrder_ftl");
	}

	@RequestMapping(params = "saveOrder")
	@ResponseBody
	public ResultMessage saveOrder(OrderItem item) {
		ResultMessage r = new ResultMessage();
		if (orderService.saveOrUpdateOrder(item)) {
			r.setSuccess(true);
		} else {
			r.setSuccess(false);
		}
		return r;
	}

	@RequestMapping(params = "deleteOrderItems")
	@ResponseBody
	public ResultMessage deleteOrderItems(String ids) {
		ResultMessage r = new ResultMessage();
		if (StringUtils.isNotEmpty(ids)) {
			String[] tempIds = ids.split(",");
			List<Long> orderIds = new ArrayList<Long>();
			for (String id : tempIds) {
				orderIds.add(Long.valueOf(id));
			}
			orderService.deleteOrderInfo(orderIds);
			r.setSuccess(true);
		} else {
			r.setSuccess(false);
		}
		return r;
	}

	@RequestMapping(params = "submitFreeOrderInfo")
	public String submitFreeOrderInfo(OrderItem item,
			MultipartHttpServletRequest request) {
		ResultMessage r = new ResultMessage();
		Iterator<String> itr = request.getFileNames();

		if (itr.hasNext()) {
			try {
				MultipartFile mpf = request.getFile(itr.next());
				if (mpf.getBytes().length > 0) {

					System.out
							.println(mpf.getOriginalFilename() + " uploaded!");

					System.out.println("length-------->"
							+ mpf.getBytes().length);
					System.out.println("bytes--------->" + mpf.getBytes());
					System.out.println("getContentType--------->"
							+ mpf.getContentType());
					System.out.println("OriginalFilename--------->"
							+ mpf.getOriginalFilename());

					String realPath = request.getSession().getServletContext()
							.getRealPath("/freeUploads");
					String imageFileName = NameUtil.getRandomImageName()
							+ ".jpg";
					String imagePath = "freeUploads/" + imageFileName;
					byte[] buff = mpf.getBytes();
					// 从字符串获取字节写入流
					InputStream is = new ByteArrayInputStream(buff);
					// int len = -1;
					// while(-1 != (len = is.read(buff))) {
					// //将字节数组转换为字符串
					// String res = new String(buff, 0, len);
					// System.out.println(res);
					// }
					FileCopyUtils.copy(buff, new File(realPath + "/"
							+ imageFileName));
//					FileUtils.copyInputStreamToFile(is, new File(realPath + "/"
//							+ imageFileName));
//
					FileCopyUtils.copy(buff, new File(
							"F:/GitHub/xdtech-sys/xdtech-web/src/main/webapp/freeUploads/"
									+ imageFileName));
					item.setImage(imagePath);
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}
		item.setOrderNo(NumberUtil.getDateSerializable());
		item.setStatus("0");
		item.setDateNeeded(new Date());
		if (orderService.saveOrUpdateOrder(item)) {
			sendEmail(item);
			r.setSuccess(true);
			r.setMsg("发表成功");
		} else {
			r.setSuccess(false);
			r.setMsg("发表失败");
		}
		request.setAttribute("r", r);
		return "redirect:show.do?to&url=thankyou";
	}

	private void sendEmail(OrderItem item) {
		try {
			JavaMailSenderImpl javaMail = new JavaMailSenderImpl();
			Properties prop = new Properties();
			// 默认商城发送用的用户和密码
			javaMail.setUsername("zzxstudynumber");
			javaMail.setPassword("zzxlp522");
			prop.setProperty("mail.smtp.host", "smtp.163.com");
			prop.setProperty("mail.smtp.auth", "true");
			javaMail.setJavaMailProperties(prop);
			MimeMessage message = javaMail.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true,
					"UTF-8");
			messageHelper.setSubject("Thank You for your custom patch inquiry "+item.getOrderNo());
			messageHelper.setSentDate(new Date());
			messageHelper.setFrom("zzxstudynumber@163.com", "英雄秀标");
			messageHelper.setTo(item.getEmail());
			StringBuffer sb = new StringBuffer();
			sb.append("<p><span style=\"font-family:arial,helvetica,sans-serif\">Dear "+item.getName()+" ,</span></p>")
			  .append("<p><span style=\"font-family:arial,helvetica,sans-serif\">My name is Seth Reed and I have been assigned to be your creative specialist for your custom patch project. I will provide you with a price quote within 24 business hours.</span></p>")
			  .append("<p><span style=\"font-family:arial,helvetica,sans-serif\">In the meantime please feel free to learn more about how our order process works by going to:</span></p>")
			  .append("<p><a href=\"http://heropatches.com/how-it-works/\" target=\"_blank\">http://heropatches.com/how-it-works/</a></p>")
			  .append("<p><span style=\"font-family:arial,helvetica,sans-serif\">Or check out our high quality patches that we have produced for other customer's in the same industry as yours by going to:</span></p>")
			  .append("<p><a href=\"http://heropatches.com/gallery-of-patches\" target=\"_blank\">http://heropatches.com/gallery-of-patches/</a></p>")
		      .append("<p><span style=\"font-family:arial,helvetica,sans-serif\">We guarantee to make the process of ordering custom patches very simple. As your creative specialist I am ready to answer any of your questions, and make suggestions. We also have a team of more than 10 professional in-house graphic designers that are at your disposal to assist you with your design if necessary.</span></p>")
			  .append("<p><span style=\"font-family:arial,helvetica,sans-serif\">We offer free shipping throughout the world and a guaranteed turnaround time of 8 - 12 business days for our Standard Delivery or 2 - 4 business days Rush Delivery after samples approval (the fastest delivery in the industry).</span></p>")
			  .append("<p><span style=\"font-family:arial,helvetica,sans-serif\">Again thank you for contacting us at Hero Patches. We look forward to producing your patch.</span></p>")
			  .append("<p><span style=\"font-family:arial,helvetica,sans-serif\"><span style=\"color:#ee3829\"><span style=\"font-size:18px\"><strong>Seth Reed</strong></span></span><br>")
			  .append("<span style=\"color:rgb(0, 0, 0)\">Patch Expert</span><br>")
			  .append("<span style=\"color:#ee3829\">Phone:</span>&nbsp;(877) 731 4711 ext 1009<br>")
			  .append("<u><a href=\"http://www.heropatches.com\" target=\"_blank\"><span style=\"color:#ee3829\">Hero Patches</span></a>&nbsp;</u>| <a href=\"http://www.facebook.com/pages/Hero-Custom-Products/320184221415734\" target=\"_blank\"><span style=\"color:#ee3829\">Facebook</span></a></span></p>");
			messageHelper.setText(sb.toString(), true);
			javaMail.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(params="myOrders")
	public String myOrders(HttpServletRequest request,Pagination pg) {
		List<CategoryItem> categoryItems = categoryService.loadCategoryItems();
		request.setAttribute("categories", categoryItems);
		Map<String, Object> results = null;
		MemberItem memberItem = (MemberItem) request.getSession().getAttribute("member");
		results = orderService.loadOrdersWithMemEmail(pg, memberItem.getEmail());
		request.setAttribute("rs", results);
		return "shop/myOrders_ftl";
	}
	
	@RequestMapping(params = "passOrder")
	@ResponseBody
	public ResultMessage passOrder(Long orderId) {
		ResultMessage r = new ResultMessage();
		r.setSuccess(orderService.passOrder(orderId));
		return r;
	}
	@RequestMapping(params = "voidOrders")
	@ResponseBody
	public ResultMessage voidOrders(String ids) {
		ResultMessage r = new ResultMessage();
		if (StringUtils.isNotEmpty(ids)) {
			String[] tempIds = ids.split(",");
			List<Long> orderIds = new ArrayList<Long>();
			for (String id : tempIds) {
				orderIds.add(Long.valueOf(id));
			}
			orderService.voidOrder(orderIds);
			r.setSuccess(true);
		} else {
			r.setSuccess(false);
		}
		return r;
	}
}
