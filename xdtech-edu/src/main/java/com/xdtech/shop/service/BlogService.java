package com.xdtech.shop.service;

import java.util.List;
import java.util.Map;

import com.xdtech.common.service.impl.IBaseService;
import com.xdtech.core.model.BaseCondition;
import com.xdtech.shop.model.Blog;
import com.xdtech.shop.vo.BlogItem;
import com.xdtech.web.model.Pagination;

/**
 * 
 * @author max.zheng
 * @create 2014-12-08 20:19:20
 * @since 1.0
 * @see
 */
public interface BlogService extends IBaseService<Blog>{

	/**
	 * 保存更新信息
	 * @author max.zheng
	 * @create 2014-12-08 20:19:20
	 * @modified by
	 * @param item
	 * @return
	 */
	boolean saveOrUpdateBlog(BlogItem item);

	/**
	 * 加载记录信息
	 * @author max.zheng
	 * @create 2014-12-08 20:19:20
	 * @modified by
	 * @param newId
	 * @return
	 */
	BlogItem loadBlogItem(Long blogId);

	/**
	 * 根据id号删除记录信息
	 * @author max.zheng
	 * @create 2014-12-08 20:19:20
	 * @modified by
	 * @param id
	 * @return
	 */
	boolean deleteBlogInfo(long id);

	/**
	 * 
	 * @author max.zheng
	 * @create 2014-12-08 20:19:20
	 * @modified by
	 * @param blogIds
	 */
	boolean deleteBlogInfo(List<Long> blogIds);

	/**
	 * 
	 * @author max.zheng
	 * @create 2014-12-11下午9:21:06
	 * @modified by
	 * @param pg
	 * @param object
	 * @return
	 */
	Map<String, Object> loadBlogsWithMemId(Pagination pg, Object object);

	/**
	 * 
	 * @author max.zheng
	 * @create 2014-12-20下午7:25:09
	 * @modified by
	 * @param pg
	 * @return
	 */
	Map<String, Object> loadBlogsPage(Pagination pg);

	/**
	 * 
	 * @author max.zheng
	 * @create 2014-12-21下午8:33:15
	 * @modified by
	 * @param blogIds
	 */
	void passBlogs(List<Long> blogIds);

	/**
	 * 
	 * @author max.zheng
	 * @create 2014-12-21下午8:33:23
	 * @modified by
	 * @param blogIds
	 */
	void voidBlogs(List<Long> blogIds);

	/**
	 * 
	 * @author max.zheng
	 * @create 2014-12-21下午8:54:17
	 * @modified by
	 * @param pg
	 * @param condition
	 * @return
	 */
	Map<String, Object> loadPageCondition(Pagination pg, BaseCondition condition);
}
