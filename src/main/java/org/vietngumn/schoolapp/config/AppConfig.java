package org.vietngumn.schoolapp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Spring JavaConfig configuration class to setup service beans in Spring container
 *
 * @author Steve Nguyen
 */
@Configuration
@ComponentScan(basePackages = {"org.vietngumn.schoolapp.service"})
public class AppConfig {

}
