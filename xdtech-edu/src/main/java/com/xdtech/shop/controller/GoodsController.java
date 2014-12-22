package com.xdtech.shop.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.xdtech.common.utils.ImageUtils;
import com.xdtech.shop.service.CategoryService;
import com.xdtech.shop.service.GoodsService;
import com.xdtech.shop.util.NameUtil;
import com.xdtech.shop.vo.CategoryItem;
import com.xdtech.shop.vo.GoodsItem;
import com.xdtech.show.vo.MemberItem;
import com.xdtech.web.model.Pagination;
import com.xdtech.web.model.ResultMessage;

/**
 * 
 * @author max.zheng
 * @create 2014-11-30 21:45:39
 * @since 1.0
 * @see
 */
@Controller
@Scope("prototype")
@RequestMapping("/goods.do")
public class GoodsController {
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private CategoryService categoryService;
	@RequestMapping(params = "goods")
	public ModelAndView skipGoods() {
		return new ModelAndView("shop/goods/goods_ftl");
	}
	
	@RequestMapping(params="loadList")
	@ResponseBody
	public Map<String, Object> loadList(HttpServletRequest request,Pagination pg,GoodsItem condition) {
		Map<String, Object> results = goodsService.loadPageCondition(pg, condition);
		return results;
	}
	
	@RequestMapping(params = "editGoods")
	public ModelAndView editGoods(HttpServletRequest request,Long goodsId) {
		if (goodsId!=null) {
			request.setAttribute("goodsItem", goodsService.loadGoodsItem(goodsId));
		}
		return new ModelAndView("shop/goods/editGoods_ftl");
	}
	
	@RequestMapping(params = "saveGoods")
	@ResponseBody
	public ResultMessage saveGoods(GoodsItem item,
			@RequestParam MultipartFile[] myfiles,HttpServletRequest request) {
		ResultMessage r = new ResultMessage();
		for (MultipartFile myfile : myfiles) {
			if (myfile.isEmpty()) {
				System.out.println("文件未上传");
			} else {
				System.out.println("文件长度: " + myfile.getSize());
				System.out.println("文件类型: " + myfile.getContentType());
				System.out.println("文件名称: " + myfile.getName());
				System.out.println("文件原名: " + myfile.getOriginalFilename());
				System.out.println("========================================");
				// 如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中
				String realPath = request.getSession().getServletContext().getRealPath("/uploads");
				String imgName = NameUtil.getRandomImageName();
				String imageFileName = imgName+".jpg";
				
				// 这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的
				try {
//					FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, myfile.getOriginalFilename()));
					FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath+"/"+imageFileName));
					String imageName150 = imgName+"_150.jpg";
					ImageUtils.scale2(realPath+"/"+imageFileName,realPath+"/"+imageName150,150, 150, true);
					//为了测试方便,同时保存一份项目工程下
					FileUtils.copyFile(new File(realPath+"/"+imageFileName), new File("F:/GitHub/xdtech-sys/xdtech-web/src/main/webapp/uploads/"+imageFileName));
					FileUtils.copyFile(new File(realPath+"/"+imageName150), new File("F:/GitHub/xdtech-sys/xdtech-web/src/main/webapp/uploads/"+imageName150));			
					item.setImages("uploads/"+imageName150);
					item.setBigImg("uploads/"+imageFileName);
				} catch (Exception e) {
					r.setSuccess(false);
					r.setMsg("商品图片处理失败");
					return r;
				}
			}
		}
		if (goodsService.saveOrUpdateGoods(item)) {
			r.setSuccess(true);
		} else {
			r.setSuccess(false);
		}
		return r;
	}
	
	@RequestMapping(params = "deleteGoodsItems")
	@ResponseBody
	public ResultMessage deleteGoodsItems(String ids) {
		ResultMessage r = new ResultMessage();
		if (StringUtils.isNotEmpty(ids)) {
			String[] tempIds = ids.split(",");
			List<Long> goodsIds = new ArrayList<Long>();
			for (String id : tempIds) {
				goodsIds.add(Long.valueOf(id));
			}
			goodsService.deleteNewInfo(goodsIds);
			r.setSuccess(true);
		}else {
			r.setSuccess(false);
		}
		return r;
	}
	/**
	 * 上架商品操作
	 * 
	 * @author max.zheng
	 * @create 2014-12-21下午3:07:27
	 * @modified by
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "putawayGoodsItems")
	@ResponseBody
	public ResultMessage putawayGoodsItems(String ids) {
		ResultMessage r = new ResultMessage();
		if (StringUtils.isNotEmpty(ids)) {
			String[] tempIds = ids.split(",");
			List<Long> goodsIds = new ArrayList<Long>();
			for (String id : tempIds) {
				goodsIds.add(Long.valueOf(id));
			}
			goodsService.putawayGoods(goodsIds);
			r.setSuccess(true);
		}else {
			r.setSuccess(false);
		}
		return r;
	}
	/**
	 * 下架商品操作
	 * 
	 * @author max.zheng
	 * @create 2014-12-21下午3:07:58
	 * @modified by
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "soldOutGoodsItems")
	@ResponseBody
	public ResultMessage soldOutGoodsItems(String ids) {
		ResultMessage r = new ResultMessage();
		if (StringUtils.isNotEmpty(ids)) {
			String[] tempIds = ids.split(",");
			List<Long> goodsIds = new ArrayList<Long>();
			for (String id : tempIds) {
				goodsIds.add(Long.valueOf(id));
			}
			goodsService.soldOutGoods(goodsIds);
			r.setSuccess(true);
		}else {
			r.setSuccess(false);
		}
		return r;
	}
	
	//前端界面查询
	@RequestMapping(params="loadGoodsByCtgId")
	public String loadGoodsByCtgId(Long ctgId,Pagination pg,HttpServletRequest request) {
		List<CategoryItem> categoryItems = categoryService.loadCategoryItems();
		request.setAttribute("categories", categoryItems);
		request.setAttribute("currentCtg", categoryService.loadCategoryItem(ctgId));
		Map<String, Object> results = goodsService.loadGoodsByCtgId(pg, ctgId);
		request.setAttribute("rs", results);
		return "shop/showGoods_ftl";
	}

}
