# DBSWeatherApplication

Please follow the following steps to built, deploy and access the application -

1) take-out DBSWeatherApp project from the repository
2) import the DBSWeatherApp project to the IDE
3) Build- mvn clean install

DEPLOYING THE APPLICATION <br />
*************************** <br />

I have built the project in Spring Tool Suite, we can import the project in STS and to deploy, we can directly right click on the project and run as 'Spring Boot App'.

The application will start on server '9010'.

Tokens to Darksky, Mongolabs have been added in the application.properties file.

Below dummy cities have also been added in ServletContext on application start-up.<br />
City (CityName, Country, Lattitude, Longitude)

"Austin", "TX", "30.3079827", "-97.8934863"<br />
"Campbell", "CA", "37.2805754", "-121.9729761"<br />
"Jakarta", "Indonesia", "-6.2293867", "106.6894322"<br />
"Nara", "Japan", "34.6580727", "135.8220505"<br />
"Niseko", "Japan", "42.7927286", "140.6145001"<br />
"Omaha", "NE", "41.292032", "-96.2213323"


ACCESSING THE APPLICATION <br />
*************************** <br />

Below are the end-points-

1) http://localhost:9010/weather/  -- loads the index page
   wherein we can <br />
   a) get weather details<br />
   b) delete old forecasts

2) http://localhost:9010/weather/details -- shows details of weather for each configured city<br />
	contains a City dropdown, on change of the city, it loads the respective city's weather information

3) http://localhost:9010/weather/deleteOldForecasts -- deletes forecasts which are 3 or more days old

	In case of error, error page will be loaded.
