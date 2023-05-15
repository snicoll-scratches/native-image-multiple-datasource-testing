package org.matthenry87.nativetesting.api.db2;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "db2EntityManagerFactory",
        transactionManagerRef = "db2TransactionManager",
        basePackages = "org.matthenry87.nativetesting.api.db2")
class Db2Config {

    @Bean(name = "db2ServerDataSource")
    @ConfigurationProperties("db2")
	HikariDataSource db2ServerDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name = "db2EntityManagerFactory")
    EntityManagerFactory db2EntityManagerFactory(@Qualifier("db2ServerDataSource") DataSource db2ServerDataSource) {

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        properties.put("generate-ddl", "true");
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(db2ServerDataSource);
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emf.setPersistenceUnitName("db2Persist");
        emf.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        emf.setPackagesToScan("org.matthenry87.nativetesting.api.db2");
        emf.setJpaPropertyMap(properties);
        emf.afterPropertiesSet();

        return emf.getObject();
    }

    @Bean(name = "db2TransactionManager")
    public PlatformTransactionManager db2TransactionManager(@Autowired @Qualifier("db2EntityManagerFactory") EntityManagerFactory db2EntityManagerFactory) {

        return new JpaTransactionManager(db2EntityManagerFactory);
    }

}

