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

package com.liferay.search.result.display;

import java.util.Optional;

import javax.portlet.PortletPreferences;

import com.liferay.search.result.util.PortletPreferencesHelper;

/**
 * @author Wade Cao
 */
public class SearchResultPreferencesImpl implements SearchResultPreferences {

	public SearchResultPreferencesImpl(
		Optional<PortletPreferences> portletPreferencesOptional) {
		_portletPreferencesHelper = new PortletPreferencesHelper(
				portletPreferencesOptional);
	}
	
	@Override
	public String getLatitude() {
		return _portletPreferencesHelper.getString(
			SearchResultPreferences.PREFERENCE_KEY_LATITUDE, "42.359849");
	}

	public String getLongitude() {
		return _portletPreferencesHelper.getString(
				SearchResultPreferences.PREFERENCE_KEY_LONGITUDE, "-71.0586345");
	};

	@Override
	public boolean isDisplayResultsInDocumentForm() {
		return false;
	}

	@Override
	public boolean isViewInContext() {
		return false;
	}

	@Override
	public boolean isHighlightEnabled() {
		return _portletPreferencesHelper.getBoolean(
			SearchResultPreferences.HIGHLIGHT_ENABLED_PREFERENCE_KEY,
			SearchResultPreferences.HIGHLIGHT_ENABLED_DEFAULT_VALUE);
	}

	private final PortletPreferencesHelper _portletPreferencesHelper;
}