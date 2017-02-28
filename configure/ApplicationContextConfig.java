package com.ciit.collaborativebackend.configure;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ciit.collaborativebackend.dao.BlogDAO;
import com.ciit.collaborativebackend.dao.UserDAO;
import com.ciit.collaborativebackend.daoimpl.BlogDAOImpl;
import com.ciit.collaborativebackend.daoimpl.UserDAOImpl;
import com.ciit.collaborativebackend.model.Blog;
import com.ciit.collaborativebackend.model.Chat;
import com.ciit.collaborativebackend.model.Event;
import com.ciit.collaborativebackend.model.Forum;
import com.ciit.collaborativebackend.model.ForumComment;
import com.ciit.collaborativebackend.model.Friend;
import com.ciit.collaborativebackend.model.Job;
import com.ciit.collaborativebackend.model.JobApplied;
import com.ciit.collaborativebackend.model.User;




@Configuration
@ComponentScan("com.ciit.collaborativebackend")
@EnableTransactionManagement
public class ApplicationContextConfig {
	private static final Logger logger = 
			LoggerFactory.getLogger(ApplicationContextConfig.class);
	
	@Bean(name = "dataSource")
	public DataSource getOracleDataSource() {
		logger.debug("Starting of the method getOracleDataSource");
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		
		dataSource.setUsername("ORACLE_COLOB"); //Schema name
		dataSource.setPassword("yush");

		Properties connectionProperties = new Properties();
		
		connectionProperties.setProperty("hibernate.dialect","org.hibernate.dialect.Oracle10gDialect");
		
		
		dataSource.setConnectionProperties(connectionProperties);
		
		logger.debug("Ending of the method getOracleDataSource");
		return dataSource;
	}

  
  	@Autowired
     @Bean(name= "sessionFactory")
     public SessionFactory getSessionFactory(DataSource dataSource)
  	{
         LocalSessionFactoryBuilder sessionBuilder= new LocalSessionFactoryBuilder(dataSource);
         //sessionBuilder.addProperties(getHibernateProperties());
         sessionBuilder.addAnnotatedClass(User.class);
         sessionBuilder.addAnnotatedClass(Blog.class);
         sessionBuilder.addAnnotatedClass(Chat.class);
         sessionBuilder.addAnnotatedClass(Event.class);
         sessionBuilder.addAnnotatedClass(Forum.class);
         sessionBuilder.addAnnotatedClass(ForumComment.class);
         sessionBuilder.addAnnotatedClass(Friend.class);
         sessionBuilder.addAnnotatedClass(Job.class);
         sessionBuilder.addAnnotatedClass(JobApplied.class);
        return sessionBuilder.buildSessionFactory();
  	
 }
		
	
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		logger.debug("Starting of the method HibernateTransactionManager");
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		logger.debug("Ending of the method HibernateTransactionManager");
		return transactionManager;
	}

	@Autowired
	@Bean(name = "userDetailsDAO")
	public UserDAO getUserDetailsDAO(SessionFactory sessionFactory) {
		logger.debug("Starting of the method userDAO");
		return new UserDAOImpl(sessionFactory);
		
	}
	
	@Autowired
	@Bean(name = "blogDetailsDAO")
	public BlogDAO getBlogDetailsDAO(SessionFactory sessionFactory) {
		logger.debug("Starting of the method blogDAO");
		return new BlogDAOImpl(sessionFactory);
		
	}



}
