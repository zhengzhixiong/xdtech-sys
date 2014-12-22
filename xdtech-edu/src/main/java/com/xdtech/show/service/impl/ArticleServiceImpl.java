package com.xdtech.show.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xdtech.core.orm.Page;
import com.xdtech.core.orm.utils.BeanUtils;
import com.xdtech.show.dao.ArticleDao;
import com.xdtech.show.model.Article;
import com.xdtech.show.service.ArticleService;
import com.xdtech.show.vo.ArticleItem;
import com.xdtech.web.model.Pagination;

/**
 * 
 * @author max.zheng
 * @create 2014-12-06 20:53:58
 * @since 1.0
 * @see
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleDao articleDao;

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-06 20:53:58
	 * @modified by
	 * @param entity
	 */
	public void save(Article entity) {
		articleDao.save(entity);
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-06 20:53:58
	 * @modified by
	 * @param entity
	 */
	public void delete(Article entity) {
		articleDao.delete(entity);
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-06 20:53:58
	 * @modified by
	 * @param id
	 */
	public void delete(Long id) {
		articleDao.delete(id);
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-06 20:53:58
	 * @modified by
	 * @param id
	 * @return
	 */
	public Article get(Long id) {
		return articleDao.get(id);
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-06 20:53:58
	 * @modified by
	 * @return
	 */
	public List<Article> getAll() {
		return articleDao.getAll();
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-06 20:53:58
	 * @modified by
	 * @param pg
	 * @param values
	 * @return
	 */
	public Map<String, Object> loadPageAndCondition(Pagination pg,
			Map<String, String> values) {
		Map<String, Object> results = new HashMap<String, Object>();
		List<ArticleItem> articles = null;
		long rows = 0;
		if (pg!=null) {
			Page<Article> page = articleDao.findPage(pg,"from Article order by createTime desc", values);
			articles = creatItems(page.getResult());//BeanUtils.copyListProperties(ArticleItem.class, page.getResult());
			rows = page.getTotalItems();
		}else {
			List<Article> articleList = articleDao.find("from Article order by id desc", values);
			articles = creatItems(articleList);//BeanUtils.copyListProperties(ArticleItem.class, articleList);
			rows = articles.size();
		}
		results.put("total", rows);
		results.put("rows", articles);
		return results;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-06 20:53:58
	 * @modified by
	 * @param item
	 * @return
	 */
	public boolean saveOrUpdateArticle(ArticleItem item) {
		Article article = null;
		if (item.getId()==null) {
			article = new Article();
		}else {
			article = articleDao.get(item.getId());
		}
		//复制前台修改的属性
		BeanUtils.copyProperties(article, item);
		articleDao.save(article);
		return true;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-06 20:53:58
	 * @modified by
	 * @param articleId
	 * @return
	 */
	public ArticleItem loadArticleItem(Long articleId) {
		Article article = articleDao.get(articleId);
		ArticleItem articleItem = new ArticleItem();
		BeanUtils.copyProperties(articleItem, article);
		return articleItem;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-06 20:53:58
	 * @modified by
	 * @param id
	 * @return
	 */
	public boolean deleteArticleInfo(long id) {
		delete(id);
		return true;
	}
	
	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-06 20:53:58
	 * @modified by
	 * @param newIds
	 * @return
	 */
	public boolean deleteArticleInfo(List<Long> articleIds) {
		for (Long id : articleIds) {
			delete(id);
		}
		return true;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-6下午11:16:30
	 * @modified by
	 * @return
	 */
	public List<ArticleItem> loadArticleItems() {
		List<Article> articles = articleDao.findByHql("from Article order by createTime desc ");
		List<ArticleItem> items = creatItems(articles);
		return items;
	}
	
	private List<ArticleItem> creatItems(List<Article> articles) {
		List<ArticleItem> items = new ArrayList<ArticleItem>();
		ArticleItem item = null;
		for (Article article : articles) {
			item = new ArticleItem();
			BeanUtils.copyProperties(item, article);
			item.setMember(article.getMember().getNickName());
			items.add(item);
		}
		return items;
	}

	/**
	 * @description
	 * @author max.zheng
	 * @create 2014-12-7下午7:49:36
	 * @modified by
	 * @param id
	 * @return
	 */
	public List<ArticleItem> loadArticleItemsByMemberId(Long id) {
		List<Article> articles = articleDao.findByHql("from Article ac where ac.member.id=?",id);
		return creatItems(articles);
	}

}
