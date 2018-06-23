<%--
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
--%>

<%@ page import="com.liferay.search.result.map.portlet.SearchResultsMapDisplayContext" %>

<%
SearchResultsMapDisplayContext searchResultsMapDisplayContext = (SearchResultsMapDisplayContext)java.util.Objects.requireNonNull(request.getAttribute(SearchResultsMapDisplayContext.ATTRIBUTE));
%>

<div id="map-canvas" style="height:500px; width:800px"></div>

<script
	src="https://maps.googleapis.com/maps/api/js?v=3&key=AIzaSyABOXmu2BMXwxNHbhHrTcMRLnOJQYpHbWQ"
	type="text/javascript"> </script>

<script>
	var ____lat = 42.359849;
	var ____lng = -71.0586345;

	var map;
	var panorama;

	var infoWindow = new google.maps.InfoWindow({
		content: 'Boston City Hall'
	});

	var labels = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
	var labelIndex = 0;

	function initialize() {
		var mapOptions = {
		zoom: 10,
		maxZoom: 18,
		center: new google.maps.LatLng(____lat, ____lng)
		};

		var bounds = new google.maps.LatLngBounds();

		var searchLocations = <%= searchResultsMapDisplayContext.getMapMarkersJSON() %>;

		map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);

	for (var i = 0; i < searchLocations.length; i++) {
		var p = searchLocations[i];
			var latlng = new google.maps.LatLng(p.lat, p.lng);
			var marker = addMarker(latlng, p.title, p.summary);

			bounds.extend(marker.position);
		}

		if (searchLocations.length > 0) {
		map.fitBounds(bounds);
		map.panToBounds(bounds);
		}
	}

	function addMarker(pos, title, summary) {
		var marker = new google.maps.Marker({
			position: pos,
			map: map,
			label: labels[labelIndex++ % labels.length],
			title: title
		});

		createInfoWindow(marker, title, summary);

		return marker;
	}

	function createInfoWindow(marker, title, summary) {
		var contentStr = '<div id="allInfo" style="width:250px;">' +
				'<div> <h2>' + title + '</h2></div><p>' + summary +
				'<p><input type="button" value="Go to Street View" onClick="streetView(\'' + marker.position + '\')"></input>' +
				'</div>';

		google.maps.event.addListener(marker, 'click', function() {
			infoWindow.setContent(contentStr);
			infoWindow.open(map,marker);
		});

	}

	function streetView(pos) {
		var posLatLng = pos.split(",");
		var plat = posLatLng[0].substring(1);
		var plng = posLatLng[1].substring(1,posLatLng[1].length-1);

		panorama = map.getStreetView();
		panorama.setPosition(new google.maps.LatLng(plat, plng));
		panorama.setPov(/** @type {google.maps.StreetViewPov} */({
			heading: 265,
			pitch: 0
		}));

		panorama.setVisible(true);
	}

	initialize();

</script>