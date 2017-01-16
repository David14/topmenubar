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
package de.interseroh.tmb.client;

import java.util.logging.Logger;

import org.gwtbootstrap3.extras.bootbox.client.Bootbox;
import org.gwtbootstrap3.extras.bootbox.client.options.BootboxLocale;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.MetaElement;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;
import de.interseroh.tmb.client.common.ServicePreparator;
import de.interseroh.tmb.client.ui.main.MainPanelView;

public class TopMenueBarWebApp implements EntryPoint {

	private static Logger logger = Logger
			.getLogger(TopMenueBarWebApp.class.getName());

	private static final String HOST_LOADING_IMAGE = "loadingImage";

	private static final String HISTORY_MAIN = "main";

	private static final String LOCALE = "de_DE";

	// Create Gin Injector
	private final TopMenueBarAppGinjector injector = GWT
			.create(TopMenueBarAppGinjector.class);

	@Override
	public void onModuleLoad() {
		addMetaElements();

		// Disable Back Button
		setupHistory();

		setupBootbox();

		initServices();

		createViews();

		removeLoadingImage();
	}

	private void addMetaElements() {
		logger.info("Add viewport");
		MetaElement element = Document.get().createMetaElement();
		element.setName("viewport");
		element.setContent("width=device-width, initial-scale=1.0");

		NodeList<Element> node = Document.get().getElementsByTagName("head");
		Element elementHead = node.getItem(0);
		elementHead.appendChild(element);
	}

	private void removeLoadingImage() {
		// Remove loadingImage from Host HTML page
		RootPanel rootPanel = RootPanel.get(HOST_LOADING_IMAGE);
		if (rootPanel != null) {
			RootPanel.getBodyElement().removeChild(rootPanel.getElement());
		}
	}

	private void initServices() {
		ServicePreparator servicePreparator = injector.getServicePreparator();
		servicePreparator.prepare();
	}

	private void setupHistory() {
		History.newItem(HISTORY_MAIN);

		// Add history listener
		History.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				String token = event.getValue();
				if (!token.equals(HISTORY_MAIN)) {
					History.newItem(HISTORY_MAIN);
				}
			}
		});
	}

	private void createViews() {
		// Views
		logger.info("Create Views begins...");

		MainPanelView mainPanelView = injector.getMainPanelView();
		mainPanelView.setContentAreaVisible(false);


		mainPanelView.setContentAreaVisible(true);

		RootPanel.get().add(mainPanelView);

		logger.info("Create Views ends...");
	}

	private void setupBootbox() {
		if (LocaleInfo.getCurrentLocale().getLocaleName().equals(LOCALE)) {
			logger.info(
					"Locale: " + LocaleInfo.getCurrentLocale().getLocaleName());
			Bootbox.setLocale(BootboxLocale.DE);
		}
	}
}
