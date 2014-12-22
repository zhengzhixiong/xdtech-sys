package com.xdtech.show.service;

import java.util.List;

import com.xdtech.common.service.impl.IBaseService;
import com.xdtech.show.model.Article;
import com.xdtech.show.vo.ArticleItem;

/**
 * 
 * @author max.zheng
 * @create 2014-12-06 20:53:58
 * @since 1.0
 * @see
 */
public interface ArticleService extends IBaseService<Article>{

	/**
	 * 保存更新信息
	 * @author max.zheng
	 * @create 2014-12-06 20:53:58
	 * @modified by
	 * @param item
	 * @return
	 */
	boolean saveOrUpdateArticle(ArticleItem item);

	/**
	 * 加载记录信息
	 * @author max.zheng
	 * @create 2014-12-06 20:53:58
	 * @modified by
	 * @param newId
	 * @return
	 */
	ArticleItem loadArticleItem(Long articleId);
	/**
	 * 加载所有的帖子信息
	 * 
	 * @author max.zheng
	 * @create 2014-12-6下午11:16:07
	 * @modified by
	 * @return
	 */
	List<ArticleItem> loadArticleItems();

	/**
	 * 根据id号删除记录信息
	 * @author max.zheng
	 * @create 2014-12-06 20:53:58
	 * @modified by
	 * @param id
	 * @return
	 */
	boolean deleteArticleInfo(long id);

	/**
	 * 
	 * @author max.zheng
	 * @create 2014-12-06 20:53:58
	 * @modified by
	 * @param articleIds
	 */
	boolean deleteArticleInfo(List<Long> articleIds);

	/**
	 * 加载会员发表的帖子
	 * @author max.zheng
	 * @create 2014-12-7下午7:49:09
	 * @modified by
	 * @param id
	 * @return
	 */
	List<ArticleItem> loadArticleItemsByMemberId(Long id);
}
