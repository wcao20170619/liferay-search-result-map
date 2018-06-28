
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.search.result.map.portlet;

import com.liferay.portal.kernel.search.Document;
import com.liferay.search.result.display.context.SearchResultSummaryDisplayContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author André de Oliveira
 */
public class SearchResultsSummariesHolder implements Serializable {

	private static final long serialVersionUID = 1L;

	public SearchResultsSummariesHolder(int capacity) {
		_map = new HashMap<>(capacity);
	}

	public SearchResultSummaryDisplayContext get(Document document) {
		return _map.get(document);
	}

	public void put(
		Document document,
		SearchResultSummaryDisplayContext searchResultSummaryDisplayContext) {

		_map.put(document, searchResultSummaryDisplayContext);
	}

	private final Map<Document, SearchResultSummaryDisplayContext> _map;

}
