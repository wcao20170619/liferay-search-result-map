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

package com.liferay.search.result.mappoc.portlet;

import java.io.Serializable;

/**
 * @author André de Oliveira
 */
public class SearchResultsMapDisplayContext implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String ATTRIBUTE = "SearchResultsMapDisplayContext";

	public SearchResultsMapDisplayContext(
		String keywords, String mapMarkersJSON) {

		_keywords = keywords;
		_mapMarkersJSON = mapMarkersJSON;
	}

	public String getKeywords() {
		return _keywords;
	}

	public String getMapMarkersJSON() {
		return _mapMarkersJSON;
	}
	
	public String getLatitude() {
		return _latitude;
	}

	public String getLongitude() {
		return _longitude;
	}
	
	public void setLatitude(String latitude) {
		this._latitude = latitude;
	}

	public void setLongitude(String longitude) {
		this._longitude = longitude;
	}
	
	private final String _keywords;
	private final String _mapMarkersJSON;
	
	private String _latitude;
	private String _longitude;

}