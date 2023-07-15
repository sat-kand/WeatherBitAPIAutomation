package com.qa.test;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.APIDefinitions.WeatherAPIClient;
import com.qa.Base.TestBase;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/**
 *
 * @author Sat Kandhaswami This is a TestNG class which tests the weather
 *         client(WeatherAPIClient.java) and prints the result as per the
 *         requirement.
 *
 */
public class WeatherAPITest extends TestBase {

    public static Response rawResponse;
    public static JsonPath jsonResponse;
    public static Logger log;

    /**
     * This method initializes the Logger class for logging mechanism
     */
    @BeforeTest
    public void setup() {
        log = Logger.getLogger(WeatherAPITest.class);
    }

    /**
     * A test method which validates status code is 200 from the response
     */
    @Test(priority = 1)
    public void validateStatusCodeIs200Test() {

        rawResponse = WeatherAPIClient.executeWeatherAPI(); // Weather client executes the weather api
        jsonResponse = WeatherAPIClient.getJsonResponse(rawResponse);
        int statusCode = WeatherAPIClient.getResponseCode(rawResponse);
        Assert.assertEquals(statusCode, STATUS_CODE_200, "API Execution failed.Status code is not 200"); // Assert status code

        log.info("Response details are as below :");
        log.info("Status code = " + statusCode);
    }

    /**
     * A test method which validates important keys are present in the response
     */
    @Test(priority=2,dependsOnMethods = "validateStatusCodeIs200Test")
    public void validateEssentialKeysTest()
    {
        Assert.assertTrue(rawResponse.asString().contains("app_temp"),"Feels like temperature is not present");
        Assert.assertTrue(rawResponse.asString().contains("temp"), "Temperature not present in response");
    }


    /**
     * A test method which validates city name from response
     */
    @Test(priority=3,dependsOnMethods = "validateStatusCodeIs200Test")
    public void validateCityNameTest()
    {
        String actualCityName=WeatherAPIClient.getValue(jsonResponse, "data[0].city_name");
        Assert.assertEquals(actualCityName,properties.getProperty("cityName"),"API response showing incorrect city name"); //Assert city name
        log.info("City Name = " +actualCityName);
        System.out.println("City Name = " +actualCityName);
    }

    /**
     * A test method which validates count from response
     */
    @Test(priority=2,dependsOnMethods = "validateStatusCodeIs200Test")
    public void validateResultsCountTest()
    {
        String count=WeatherAPIClient.getValue(jsonResponse, "count");
        Assert.assertEquals(count,properties.getProperty("count"),"API response showing incorrect count"); //Assert count
        log.info("Count =   " +count);
        Assert.assertTrue(rawResponse.asString().contains("count"));
    }

    @AfterTest
    public void cleanUp()
    {
        System.out.println("Please refer Result.log for the result of execution");
    }
}
