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
package de.interseroh.tmb.client.common;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.ScriptInjector;

import java.util.logging.Logger;

public class RemoteScriptInjector {
	private static final Logger logger = Logger
			.getLogger(RemoteScriptInjector.class.getName());

	public void injectScript(String applicationUrl, String scriptPath) {
		String scriptFullUrl = applicationUrl + scriptPath;
		logger.info("Start JavaScript injecting  from URL: " + scriptFullUrl);
		ScriptInjector.fromUrl(scriptFullUrl)
				.setCallback(new Callback<Void, Exception>() {
					@Override
					public void onFailure(Exception reason) {
						logger.warning(
								"Error: Can not load JavaScript from from URL: "
										+ scriptFullUrl);
					}

					@Override
					public void onSuccess(Void result) {
						logger.info("Java Script is loaded from URL: "
								+ scriptFullUrl);
					}
				}).setWindow(ScriptInjector.TOP_WINDOW).inject();
	}
}
