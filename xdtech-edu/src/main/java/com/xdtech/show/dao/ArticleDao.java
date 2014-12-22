package com.xdtech.show.dao;

import org.springframework.stereotype.Repository;

import com.xdtech.core.orm.hibernate.HibernateDao;
import com.xdtech.show.model.Article;

/**
 * 
 * @author max.zheng
 * @create 2014-12-06 20:53:58
 * @since 1.0
 * @see
 */
@Repository
public class ArticleDao extends HibernateDao<Article, Long>{

}
