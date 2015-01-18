package com.xdtech.shop.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xdtech.core.dao.BaseDao;
import com.xdtech.core.model.BaseCondition;
import com.xdtech.core.orm.Page;
import com.xdtech.core.orm.utils.BeanUtils;
import com.xdtech.shop.dao.BlogDao;
import com.xdtech.shop.model.Blog;
import com.xdtech.shop.model.Goods;
import com.xdtech.shop.service.BlogService;
import com.xdtech.shop.vo.BlogItem;
import com.xdtech.shop.vo.GoodsItem;
import com.xdtech.show.model.Member;
import com.xdtech.web.model.Pagination;

/**
 * 
 * @author max.zheng
 * @create 2014-12-08 20:19:20
 * @since 1.0
 * @see
 */
@Service
@Transactional
public class BlogServiceImpl implements BlogService {
	@Autowired
	private BlogDao blogDao;
	@Autowired
	private BaseDao<Blog> baseDao;

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-08 20:19:20
	 * @modified by
	 * @param entity
	 */
	public void save(Blog entity) {
		blogDao.save(entity);
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-08 20:19:20
	 * @modified by
	 * @param entity
	 */
	public void delete(Blog entity) {
		blogDao.delete(entity);
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-08 20:19:20
	 * @modified by
	 * @param id
	 */
	public void delete(Long id) {
		blogDao.delete(id);
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-08 20:19:20
	 * @modified by
	 * @param id
	 * @return
	 */
	public Blog get(Long id) {
		return blogDao.get(id);
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-08 20:19:20
	 * @modified by
	 * @return
	 */
	public List<Blog> getAll() {
		return blogDao.getAll();
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-08 20:19:20
	 * @modified by
	 * @param pg
	 * @param values
	 * @return
	 */
	public Map<String, Object> loadPageAndCondition(Pagination pg,
			Map<String, String> values) {
		Map<String, Object> results = new HashMap<String, Object>();
		List<Object> blogs = null;
		long rows = 0;
		if (pg!=null) {
			Page<Blog> page = blogDao.findPage(pg,"from Blog order by createTime desc", values);
			blogs = BeanUtils.copyListProperties(
					BlogItem.class, page.getResult());
			rows = page.getTotalItems();
		}else {
			List<Blog> blogList = blogDao.find("from Blog order by id desc", values);
			blogs = BeanUtils.copyListProperties(
					BlogItem.class, blogList);
			rows = blogs.size();
		}
		results.put("total", rows);
		results.put("rows", blogs);
		return results;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-08 20:19:20
	 * @modified by
	 * @param item
	 * @return
	 */
	public boolean saveOrUpdateBlog(BlogItem item) {
		Blog blog = null;
		if (item.getId()==null) {
			//状态0待审核 1审核通过
			item.setStatus("0");
			blog = new Blog();
		}else {
			blog = blogDao.get(item.getId());
		}
		//复制前台修改的属性
		BeanUtils.copyProperties(blog, item);
		if (item.getMemberId()!=null) {
			blog.setMember(new Member(item.getMemberId()));
		}
		blogDao.save(blog);
		return true;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-08 20:19:20
	 * @modified by
	 * @param blogId
	 * @return
	 */
	public BlogItem loadBlogItem(Long blogId) {
		Blog blog = blogDao.get(blogId);
		BlogItem blogItem = new BlogItem();
		BeanUtils.copyProperties(blogItem, blog);
		return blogItem;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-08 20:19:20
	 * @modified by
	 * @param id
	 * @return
	 */
	public boolean deleteBlogInfo(long id) {
		delete(id);
		return true;
	}
	
	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-08 20:19:20
	 * @modified by
	 * @param newIds
	 * @return
	 */
	public boolean deleteBlogInfo(List<Long> blogIds) {
		for (Long id : blogIds) {
			delete(id);
		}
		return true;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-11下午9:21:20
	 * @modified by
	 * @param pg
	 * @param object
	 * @return
	 */
	public Map<String, Object> loadBlogsWithMemId(Pagination pg, Object values) {
		Map<String, Object> results = new HashMap<String, Object>();
		List<Object> blogs = null;
		long rows = 0;
		if (pg!=null) {
			Page<Blog> page = blogDao.findPage(pg,"from Blog b where b.member.id=? order by createTime desc", values);
			blogs = BeanUtils.copyListProperties(
					BlogItem.class, page.getResult());
			rows = page.getTotalItems();
			results.put("page", pg.getPage());
			results.put("next",pg.getPage()*pg.getRows()<rows);
			results.put("previous", pg.getPage()>1);
		}
		results.put("total", rows);
		results.put("rows", blogs);
		return results;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-20下午7:25:21
	 * @modified by
	 * @param pg
	 * @return
	 */
	public Map<String, Object> loadBlogsPage(Pagination pg) {
		Map<String, Object> results = new HashMap<String, Object>();
		List<Object> blogs = null;
		long rows = 0;
		if (pg!=null) {
			//审核通过的blog
			Page<Blog> page = blogDao.findPage(pg,"from Blog b where b.status='1' order by createTime desc");
			blogs = BeanUtils.copyListProperties(
					BlogItem.class, page.getResult());
			rows = page.getTotalItems();
			results.put("page", pg.getPage());
			results.put("next",pg.getPage()*pg.getRows()<rows);
			results.put("previous", pg.getPage()>1);
		}
		results.put("total", rows);
		results.put("rows", blogs);
		return results;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-21下午8:33:34
	 * @modified by
	 * @param blogIds
	 */
	public void passBlogs(List<Long> blogIds) {
		for (Long blogId : blogIds) {
			Blog blog = blogDao.get(blogId);
			//审核通过是1
			blog.setStatus("1");
			blog.setUpdateTime(new Date());
		}
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-21下午8:33:34
	 * @modified by
	 * @param blogIds
	 */
	public void voidBlogs(List<Long> blogIds) {
		for (Long blogId : blogIds) {
			Blog blog = blogDao.get(blogId);
			//审核失败是-1
			blog.setStatus("-1");
			blog.setUpdateTime(new Date());
		}
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-21下午8:54:48
	 * @modified by
	 * @param pg
	 * @param condition
	 * @return
	 */
	public Map<String, Object> loadPageCondition(Pagination pg,
			BaseCondition condition) {
		Map<String, Object> results = new HashMap<String, Object>();
		List<Object> goodsList = null;
		long rows = 0;
		Page<Blog> page = baseDao.findPageByNamedQuery(pg,"shop.blog.getByCondition", condition);
		goodsList = BeanUtils.copyListProperties(BlogItem.class, page.getResult());
		rows = page.getTotalItems();
		results.put("total", rows);
		results.put("rows", goodsList);
		return results;
	}

}
