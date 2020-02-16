package ru.stoupin.multidb;

import java.util.HashMap;

import javax.sql.DataSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration

@EntityScan("ru.stoupin.multidb.domain2")
@EnableJpaRepositories(
	    basePackages ="ru.stoupin.multidb.repository2", 
	    entityManagerFactoryRef = "domain2EntityManager",
	    transactionManagerRef = "domain2TransactionManager"
	)
public class ConfigDb2 {

    @Autowired
    private Environment env;
 
    @Bean
    public LocalContainerEntityManagerFactoryBean domain2EntityManager() {
        LocalContainerEntityManagerFactoryBean em
          = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(domain2DataSource());
        
        em.setPackagesToScan(
          new String[] { "ru.stoupin.multidb.domain2" });
 
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
            
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto",
          env.getProperty("spring.domain2.hibernate.ddl-auto"));
        properties.put("hibernate.dialect",
          env.getProperty("spring.domain2.hibernate.dialect"));
        
        properties.put("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class.getName());
        properties.put("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getName());
     
        
   
        em.setJpaPropertyMap(properties);
        
        return em;
    }
    
    
    @Bean
    public PlatformTransactionManager domain2TransactionManager() {
  
        JpaTransactionManager transactionManager
          = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
        		domain2EntityManager().getObject());
        return transactionManager;
    }    
    
    @Bean
    public DataSource domain2DataSource() {
  
        DriverManagerDataSource dataSource
          = new DriverManagerDataSource();
        dataSource.setDriverClassName(
          env.getProperty("spring.domain2.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.domain2.jdbc.url"));
        dataSource.setUsername(env.getProperty("spring.domain2.jdbc.username"));
        dataSource.setPassword(env.getProperty("spring.domain2.jdbc.password"));
 
        return dataSource;
    }    
}
