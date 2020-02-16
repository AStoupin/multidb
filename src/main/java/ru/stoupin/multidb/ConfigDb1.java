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
@EnableJpaRepositories(
	    basePackages ="ru.stoupin.multidb.repository1", 
	    entityManagerFactoryRef = "domain1EntityManager",
	    transactionManagerRef = "domain1TransactionManager"
	    
	    
		)
public class ConfigDb1 {

	
	
    @Autowired
    private Environment env;
 
    @Bean
    public LocalContainerEntityManagerFactoryBean domain1EntityManager() {
        LocalContainerEntityManagerFactoryBean em
          = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(domain1DataSource());
        
        em.setPackagesToScan(
          new String[] { "ru.stoupin.multidb.domain1" });
 
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
            
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto",
          env.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.dialect",
          env.getProperty("spring.jpa.properties.hibernate.dialect"));
        
        properties.put("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class.getName());
        properties.put("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getName());
       
        em.setJpaPropertyMap(properties);
        
        return em;
    }
    
    
    @Bean
    public PlatformTransactionManager domain1TransactionManager() {
  
        JpaTransactionManager transactionManager
          = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
        		domain1EntityManager().getObject());
        return transactionManager;
    }    
    
    @Bean
    public DataSource domain1DataSource() {
  
        DriverManagerDataSource dataSource
          = new DriverManagerDataSource();
        dataSource.setDriverClassName(
          env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
 
        return dataSource;
    }    	
}
