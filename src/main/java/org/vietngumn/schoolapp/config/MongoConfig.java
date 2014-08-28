package org.vietngumn.schoolapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

/**
 * Spring JavaConfig configuration class to setup MongoDb database connection in Spring container
 *
 * @author Steve Nguyen
 */
@Configuration
@EnableMongoRepositories(basePackages = {"org.vietngumn.schoolapp.repository"})
class MongoConfig extends AbstractMongoConfiguration {

	/**
	 * @see org.springframework.data.mongodb.config.AbstractMongoConfiguration#getDatabaseName()
	 */
	@Override
	protected String getDatabaseName() {
		return "vietngumn";
	}

	/**
	 * @see org.springframework.data.mongodb.config.AbstractMongoConfiguration#mongo()
	 */
	@Override
	public Mongo mongo() throws Exception {
		Mongo mongo = new MongoClient("localhost");
		mongo.setWriteConcern(WriteConcern.SAFE);
		return mongo;
	}
	
	@Override
	protected String getMappingBasePackage() {
		return "org.vietngumn.schoolapp.domain";
	}

}