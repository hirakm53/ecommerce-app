package com.hirak.ecommerce.app.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

/**
 * @author hirak_mahanta
 *
 */
@Configuration
@EnableTransactionManagement
@EnableConfigurationProperties
public class DatasourceConfiguration {

	/**
	 * Persistence unit name.
	 */
	private static final String PERSISTENCE_UNIT = "ecommercePersistenceUnit";

	/**
	 * Constant Entity Manager.
	 */
	protected static final String ENTITY_MANAGER = "ecommerceEntityManager";

	/**
	 * Entity packages
	 */
	private String entityPackage = "com.hirak.ecommerce.app.model";

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * Constant Transaction Manager.
	 */
	public static final String TX_MANAGER = "ecommerceTxManager";

	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSourceProperties datasourceProperties() {
		logger.info("Reading Data Source Properties.");
		return new DataSourceProperties();
	}

	@Primary
	@Bean(name = "ecommerceDatasource", destroyMethod = "close")
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	public HikariDataSource ecommerceDatasource() {
		logger.info("Creating Hikari Data Source.");
		return (HikariDataSource) this.datasourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class)
				.build();
	}

	/**
	 * Build EntityManagerFactory.
	 *
	 * @param builder EntityManagerFactoryBuilder
	 * @return EntityManager
	 */
	@Primary
	@Bean(name = ENTITY_MANAGER, destroyMethod = "destroy")
	public LocalContainerEntityManagerFactoryBean ecommerceEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		logger.info("Creating {} for entity packages {}.", ENTITY_MANAGER, this.getEntityPackage());
		return builder.dataSource(this.ecommerceDatasource()).properties(this.hibernateProperties())
				.packages(this.getEntityPackage().split(",")).persistenceUnit(PERSISTENCE_UNIT).build();
	}

	/**
	 *
	 * @param entityManagerFactory ds
	 * @return TxManager
	 */
	@Primary
	@Bean(name = TX_MANAGER)
	public PlatformTransactionManager ecommerceTxManager(
			@Qualifier(ENTITY_MANAGER) EntityManagerFactory entityManagerFactoryMain) {
		logger.info("Creating {}.", TX_MANAGER);
		return new JpaTransactionManager(entityManagerFactoryMain);
	}

	@EnableJpaRepositories(entityManagerFactoryRef = DatasourceConfiguration.ENTITY_MANAGER, transactionManagerRef = DatasourceConfiguration.TX_MANAGER, basePackages = "com.hirak.ecommerce.app")
	static class JpaRepositoriesConfig {
	}

	/**
	 *
	 * @return hibernateProperties
	 */
	private Map<String, Object> hibernateProperties() {
		final Resource resource = new ClassPathResource("hibernateMetro.properties");
		try {
			final Properties properties = PropertiesLoaderUtils.loadProperties(resource);
			return properties.entrySet().stream()
					.collect(Collectors.toMap(e -> e.getKey().toString(), e -> e.getValue()));
		} catch (final IOException e) {
			logger.error("Unable to read Hibernate Properties", e);
			return new HashMap<>();
		}
	}

	public String getEntityPackage() {
		return entityPackage;
	}

	public void setEntityPackage(String entityPackage) {
		this.entityPackage = entityPackage;
	}

}
