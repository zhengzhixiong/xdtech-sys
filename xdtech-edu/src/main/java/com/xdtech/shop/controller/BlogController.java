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

import com.xdtech.shop.service.BlogService;
import com.xdtech.shop.service.CategoryService;
import com.xdtech.shop.util.NameUtil;
import com.xdtech.shop.vo.BlogItem;
import com.xdtech.shop.vo.CategoryItem;
import com.xdtech.show.vo.MemberItem;
import com.xdtech.web.model.Pagination;
import com.xdtech.web.model.ResultMessage;

/**
 * 
 * @author max.zheng
 * @create 2014-12-08 20:19:20
 * @since 1.0
 * @see
 */
@Controller
@Scope("prototype")
@RequestMapping("/blog.do")
public class BlogController {
	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;
	@RequestMapping(params = "blog")
	public ModelAndView skipBlog() {
		return new ModelAndView("shop/blog/blog_ftl");
	}
	
	@RequestMapping(params="loadList")
	@ResponseBody
	public Map<String, Object> loadList(HttpServletRequest request,Pagination pg,BlogItem condition) {
		Map<String, Object> results = blogService.loadPageCondition(pg, condition);
		return results;
	}
	
	@RequestMapping(params = "editBlog")
	public ModelAndView editBlog(HttpServletRequest request,Long blogId) {
		if (blogId!=null) {
			request.setAttribute("blogItem", blogService.loadBlogItem(blogId));
		}
		return new ModelAndView("shop/blog/editBlog_ftl");
	}
	
	
	
	@RequestMapping(params = "saveBlog")
	@ResponseBody
	public ResultMessage saveBlog(BlogItem item,@RequestParam MultipartFile imageFile,HttpServletRequest request) {
		ResultMessage r = new ResultMessage();
		if (imageFile!=null&&imageFile.getSize()>0) {
			System.out.println("文件长度: " + imageFile.getSize());
			System.out.println("文件类型: " + imageFile.getContentType());
			System.out.println("文件名称: " + imageFile.getName());
			System.out.println("文件原名: " + imageFile.getOriginalFilename());
			System.out.println("========================================");
			// 如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中
			
			String realPath = request.getSession().getServletContext().getRealPath("/blogUploads");
			// 这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的
			try {
				
				String imageFileName = NameUtil.getRandomImageName()+".jpg";
				String imagePath = "blogUploads/"+imageFileName;
				FileUtils.copyInputStreamToFile(imageFile.getInputStream(), new File(realPath+"/"+imageFileName));
				//为了测试方便,同时保存一份项目工程下
				FileUtils.copyInputStreamToFile(imageFile.getInputStream(), new File("F:/GitHub/xdtech-sys/xdtech-web/src/main/webapp/blogUploads/"+imageFileName));
				item.setImage(imagePath);
			} catch (IOException e) {
				r.setSuccess(false);
				r.setMsg("商品图片处理失败");
				return r;
			}
		}
		if (blogService.saveOrUpdateBlog(item)) {
			r.setSuccess(true);
		}else {
			r.setSuccess(false);
		}
		return r;
	}
	
	@RequestMapping(params = "deleteBlogItems")
	@ResponseBody
	public ResultMessage deleteBlogItems(String ids) {
		ResultMessage r = new ResultMessage();
		if (StringUtils.isNotEmpty(ids)) {
			String[] tempIds = ids.split(",");
			List<Long> blogIds = new ArrayList<Long>();
			for (String id : tempIds) {
				blogIds.add(Long.valueOf(id));
			}
			blogService.deleteBlogInfo(blogIds);
			r.setSuccess(true);
		}else {
			r.setSuccess(false);
		}
		return r;
	}
	
	@RequestMapping(params = "saveBlog2")
	public ModelAndView saveBlog2(BlogItem item,@RequestParam MultipartFile imageFile,HttpServletRequest request) {
		ResultMessage r = new ResultMessage();
		if (imageFile!=null&&"image/jpeg".equals(imageFile.getContentType())) {
//			System.out.println("文件长度: " + imageFile.getSize());
//			System.out.println("文件类型: " + imageFile.getContentType());
//			System.out.println("文件名称: " + imageFile.getName());
//			System.out.println("文件原名: " + imageFile.getOriginalFilename());
//			System.out.println("========================================");
			// 如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中
			
			String realPath = request.getSession().getServletContext().getRealPath("/blogUploads");
			// 这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的
			try {
				
				String imageFileName = NameUtil.getRandomImageName()+".jpg";
				String imagePath = "blogUploads/"+imageFileName;
				FileUtils.copyInputStreamToFile(imageFile.getInputStream(), new File(realPath+"/"+imageFileName));
				//为了测试方便,同时保存一份项目工程下
				FileUtils.copyInputStreamToFile(imageFile.getInputStream(), new File("F:/GitHub/xdtech-sys/xdtech-web/src/main/webapp/blogUploads/"+imageFileName));
				item.setImage(imagePath);
			} catch (IOException e) {
				r.setSuccess(false);
				r.setMsg("商品图片处理失败");
			}
		}
		if (blogService.saveOrUpdateBlog(item)) {
			r.setSuccess(true);
			r.setMsg("发表成功");
		}else {
			r.setSuccess(false);
			r.setMsg("发表失败");
		}
//		List<CategoryItem> categoryItems = categoryService.loadCategoryItems();
//		request.setAttribute("categories", categoryItems);
		request.setAttribute("r", r);
		return new ModelAndView("shop/postBlog_ftl");
	}
	
	@RequestMapping(params="myBlogs")
	public String myBlogs(HttpServletRequest request,Pagination pg) {
		
		Map<String, Object> results = null;
		MemberItem memberItem = (MemberItem) request.getSession().getAttribute("member");
		results = blogService.loadBlogsWithMemId(pg, memberItem.getId());
		request.setAttribute("bm", results);
		return "shop/myBlogs_ftl";
	}
	
	@RequestMapping(params="loadBlogs")
	public String loadBlogs(HttpServletRequest request,Pagination pg) {
		List<CategoryItem> categoryItems = categoryService.loadCategoryItems();
		request.setAttribute("categories", categoryItems);
		Map<String, Object> results = null;
		results = blogService.loadBlogsPage(pg);
		request.setAttribute("rs", results);
		return "shop/blog_ftl";
	}
	
	@RequestMapping(params = "passBlogs")
	@ResponseBody
	public ResultMessage passBlogs(String ids) {
		ResultMessage r = new ResultMessage();
		if (StringUtils.isNotEmpty(ids)) {
			String[] tempIds = ids.split(",");
			List<Long> blogIds = new ArrayList<Long>();
			for (String id : tempIds) {
				blogIds.add(Long.valueOf(id));
			}
			blogService.passBlogs(blogIds);
			r.setSuccess(true);
		}else {
			r.setSuccess(false);
		}
		return r;
	}
	@RequestMapping(params = "voidBlogs")
	@ResponseBody
	public ResultMessage voidBlogs(String ids) {
		ResultMessage r = new ResultMessage();
		if (StringUtils.isNotEmpty(ids)) {
			String[] tempIds = ids.split(",");
			List<Long> blogIds = new ArrayList<Long>();
			for (String id : tempIds) {
				blogIds.add(Long.valueOf(id));
			}
			blogService.voidBlogs(blogIds);
			r.setSuccess(true);
		} else {
			r.setSuccess(false);
		}
		return r;
	}

}
