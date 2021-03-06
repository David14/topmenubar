/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package de.interseroh.tmb.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;
import java.util.Arrays;

@Configuration
public class LoggingCrossOriginConfiguration {

	@Value("${server.context-path:}")
	private String contextPath;

	@Bean(name = "loggingFilter")
	public CrossOriginLoggingFilter crossOriginFilter() {
		return new CrossOriginLoggingFilter();
	}

	@Bean
	public FilterRegistrationBean filterRegistrationForCrossOriginLoggingFilter(
			CrossOriginLoggingFilter filter) {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setDispatcherTypes(DispatcherType.REQUEST,
				DispatcherType.FORWARD, DispatcherType.ERROR,
				DispatcherType.ASYNC, DispatcherType.INCLUDE);
		registration.setUrlPatterns(Arrays.asList(
				//				contextPath + CommonServiceEndpoint.LOGGING_CONTEXTPATH,
				//				contextPath + "/*",
				"/*"));
		registration.setFilter(filter);
		return registration;
	}
}
