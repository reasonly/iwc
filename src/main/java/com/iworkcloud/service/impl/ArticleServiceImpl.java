package com.iworkcloud.service.impl;

import com.iworkcloud.mapper.BaseMapper;
import com.iworkcloud.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
* Title: ArticleServiceImpl
* Description: 
* 小组操作实现类
* Version:1.0.0  

 */
@Service
public class ArticleServiceImpl extends BaseServiceImpl<Article> implements BaseService<Article> {

	@Autowired
	private ArticleMapper articleMapper;

	@Override
	protected BaseMapper<Article> getMapper() {
		return this.articleMapper;
	}
}
