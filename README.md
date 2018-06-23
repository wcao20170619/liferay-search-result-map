# GEOLOCATION DEMO DATASET RESULT MAP

Clone this repository

[Create a Liferay Workspace with the Blade CLI if you don't have one](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/creating-a-liferay-workspace-with-blade-cli)

Note: the Liferay Workspace should be configured in an empty directory - *not* under portal-master.

Move this project under `modules` in your Liferay Workspace (the one you configured above) 

Build this project:

`gw deploy`

Deploy the resulting bundle (see `osgi/modules`) into a Liferay Portal

Start Liferay Portal

[Open the gogo shell](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell)

Confirm this module has been loaded:

`lb | grep "Liferay Search Result Map"`

In case the project doesn't build correctly nor the bundle doesn't start 
immediately after deployment, double check module versions in `bnd.bnd`,
dependencies versions in `build.gradle`, 
and the versions of the same packages deployed in the Portal.

If you're using elasticsearch-head to run this query, make sure you're using
the `POST` method instead of `GET`.

# SOURCE URLS

https://data.cityofboston.gov/City-Services/311-Service-Requests/awu8-dc52
https://data.cityofboston.gov/browse?limitTo=datasets&utf8=✓
https://data.cityofboston.gov/resource/awu8-dc52.json

# APIS

https://github.com/socrata/datasync
https://github.com/socrata/soda-java

# APP URL

http://www.cityofboston.gov/doit/apps/citizensconnect.asp

