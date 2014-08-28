package org.vietngumn.schoolapp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Spring JavaConfig configuration class to setup controller beans in Spring container
 *
 * @author Steve Nguyen
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"org.vietngumn.schoolapp.rest.controller"})
public class RestConfig {}
