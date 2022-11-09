package com.cdvtc.news.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionUtil {
	public static SqlSession getSqlSession(){
		SqlSession sqlSession = null;
		try {
			//获取核心配置文件的输入流
			InputStream is =  Resources.getResourceAsStream("mybatis-config.xml");
			//获取sqlSessionFactoryBuilder对象
			SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
			//获取SqlSessionFactory对象
			SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
			//获取sql的会话对象SqlSession(自动提交事务) 是mybatis提供操作数据库的对象
			sqlSession = sqlSessionFactory.openSession(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return  sqlSession;
	}


}
