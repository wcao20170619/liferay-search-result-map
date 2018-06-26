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

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.search.summary.SummaryBuilderFactory;
import com.liferay.portal.search.web.portlet.shared.search.PortletSharedSearchRequest;
import com.liferay.portal.search.web.portlet.shared.search.PortletSharedSearchResponse;
import com.liferay.search.result.display.builder.MapMarkersBuilder;
import com.liferay.search.result.util.SearchUtil;

import java.io.IOException;
import java.util.Optional;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author André de Oliveira
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.css-class-wrapper=" +
			SearchResultsMapPortletKeys.CSS_CLASS_WRAPPER,
		"com.liferay.portlet.display-category=com.liferay.geolocation.bulk.category",
		"com.liferay.portlet.icon=/icons/search.png",
		"com.liferay.portlet.instanceable=true",
		"com.liferay.portlet.layout-cacheable=true",
		"com.liferay.portlet.preferences-owned-by-group=true",
		"com.liferay.portlet.private-request-attributes=false",
		"com.liferay.portlet.private-session-attributes=false",
		"com.liferay.portlet.restore-current-view=false",
		"com.liferay.portlet.use-default-template=true",
		"javax.portlet.display-name=" +
			SearchResultsMapPortletKeys.DISPLAY_NAME,
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=" +
			SearchResultsMapPortletKeys.VIEW_TEMPLATE,
		"javax.portlet.name=" + SearchResultsMapPortletKeys.PORTLET_NAME,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=guest,power-user,user",
		"javax.portlet.supports.mime-type=text/html"
	},
	service = Portlet.class
)
public class SearchResultsMapPortlet extends MVCPortlet {

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		PortletSharedSearchResponse portletSharedSearchResponse =
			portletSharedSearchRequest.search(renderRequest);

		SearchResultsMapDisplayContext searchResultsMapDisplayContext =
			buildDisplayContext(
				portletSharedSearchResponse, renderRequest, renderResponse);

		renderRequest.setAttribute(
			SearchResultsMapDisplayContext.ATTRIBUTE,
			searchResultsMapDisplayContext);

		super.render(renderRequest, renderResponse);
	}

	protected SearchResultsMapDisplayContext buildDisplayContext(
		PortletSharedSearchResponse portletSharedSearchResponse,
		RenderRequest renderRequest, RenderResponse renderResponse) 
			throws PortletException {

		SearchResultPreferences searchResultPreferences =
			new SearchResultPreferencesImpl(
				portletSharedSearchResponse.getPortletPreferences(
					renderRequest));
		
		String mapMarkersJSON = buildMapMarkersJSON(
			renderRequest, renderResponse, 
			portletSharedSearchResponse, searchResultPreferences);

		Optional<String> keywordsOptional =
			portletSharedSearchResponse.getKeywordsOptional();

		String keywords = keywordsOptional.orElse(StringPool.BLANK);

		SearchResultsMapDisplayContext searchResultsMapDisplayContext =
			new SearchResultsMapDisplayContext(keywords, mapMarkersJSON);
		
		searchResultsMapDisplayContext.setLatitude(
			searchResultPreferences.getLatitude());
		searchResultsMapDisplayContext.setLongitude(
			searchResultPreferences.getLongitude());
		
		return searchResultsMapDisplayContext;
	}

	protected String buildMapMarkersJSON(
		RenderRequest renderRequest, RenderResponse renderResponse,
		PortletSharedSearchResponse portletSharedSearchResponse,
		SearchResultPreferences searchResultPreferences) 
			throws PortletException {

		MapMarkersBuilder mapMarkersBuilder = new MapMarkersBuilder();

		mapMarkersBuilder.setHighlightEnabled(true);
		mapMarkersBuilder.setLocale(renderRequest.getLocale());
		mapMarkersBuilder.setRenderRequest(renderRequest);
		mapMarkersBuilder.setRenderResponse(renderResponse);
		mapMarkersBuilder.setPortletURL(
			SearchUtil.getPortletURL(renderRequest, renderResponse));
		mapMarkersBuilder.setSummaryBuilderFactory(summaryBuilderFactory);
		mapMarkersBuilder.setSearchResultPreferences(searchResultPreferences);
		
		String mapMarkersJSON = mapMarkersBuilder.buildMapMarkersJSON(
			portletSharedSearchResponse.getDocuments());

		return mapMarkersJSON;
	}
	
	@Reference
	protected PortletSharedSearchRequest portletSharedSearchRequest;

	@Reference
	protected SummaryBuilderFactory summaryBuilderFactory;

}