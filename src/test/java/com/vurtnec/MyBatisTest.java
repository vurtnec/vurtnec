package com.vurtnec;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.vurtnec.model.bean.User;
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
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		// test insert
		System.out.println("test insert...");
//		User user1 = new User();
//		user1.setUserName("test");
//		user1.setUserPassword("1234567");
//		userMapper.insertUser(user1);
		// it is a must or no data will be insert into server.
		sqlSession.commit();
		
		System.out.println("ready to select...");
		// test select
		User user = userMapper.findByName("vurtnec");
		System.out.println(user.getUserName());


	}
}