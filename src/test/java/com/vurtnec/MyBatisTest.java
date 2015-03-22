package com.vurtnec;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.vurtnec.model.bean.Article;
import com.vurtnec.model.impl.ArticleMapper;
import com.vurtnec.model.impl.CategoryMapper;
import com.vurtnec.model.impl.UserMapper;

/**
 * myBatis数据库测试
 *
 */
public class MyBatisTest {
	/**
	 * 获得MyBatis SqlSessionFactory
	 * SqlSessionFactory负责创建SqlSession，一旦创建成功，就可以用SqlSession实例来执行映射语句
	 * ，commit，rollback，close等方法。
	 * 
	 * @return
	 */
	private static SqlSessionFactory getSessionFactory() {
		SqlSessionFactory sessionFactory = null;
		String resource = "mybatis/configuration.xml";
		try {
			sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader(resource));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sessionFactory;
	}

	public static void main(String[] args) {
		SqlSession sqlSession = getSessionFactory().openSession();
		try {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			
			System.out.println("test start...");
			
	//		User user1 = new User();
	//		user1.setUserName("test111");
	//		user1.setUserPassword("1234567");
	//		userMapper.insertUser(user1);
	//		sqlSession.commit();
				
			// test select
			System.out.println(userMapper.findByName("vurtnec"));
//			System.out.println(userMapper.login("vurtnec", "teamozj83266"));
		
			CategoryMapper categoryMapper = sqlSession.getMapper(CategoryMapper.class);
			
	//		Category category = new Category();
	//		category.setCategoryName("test category");
	//		category.setCategoryImage("img/post-bg.jpg");
	//		categoryMapper.insertCategory(category);
	//		sqlSession.commit();
			
			System.out.println(categoryMapper.findCategoryById(1));
			System.out.println(categoryMapper.findAllCategory());
	
			System.out.println("test done...");
			
			ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
			
//			Article article = new Article();
//			article.setArticleTitle("Man must explore, and this is exploration at its greatest");
//			article.setArticleSubTitle("Problems look mighty small from 150 miles up");
//			article.setArticleAuthor("vurtnec");
//			article.setArticleContent("Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest Man must explore, and this is exploration at its greatest ");
//			article.setArticleCreateTime(new Timestamp(new Date().getTime()));
//			article.setArticleImage("img/post-bg.jpg");
//			article.setCategoryId(1);
//			articleMapper.insertArticle(article);
//			articleMapper.insertArticle(article);
//			articleMapper.insertArticle(article);
//			articleMapper.insertArticle(article);
//			sqlSession.commit();
			
			System.out.println(articleMapper.findAllArticleOrderByTime());
			System.out.println(articleMapper.findArticleById(1));
			System.out.println(articleMapper.findArticleByCategoryIdOrderByTime(1));
		} finally {
			sqlSession.close();
		}
	}
}