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
     * A test method which validates response code from the response
     */
    @Test(priority = 1)
    public void validateResponseCodeTest() {

        rawResponse = WeatherAPIClient.executeWeatherAPI(); // Weather client executes the weather api
        jsonResponse = WeatherAPIClient.getJsonResponse(rawResponse);
        int statusCode = WeatherAPIClient.getResponseCode(rawResponse);
        Assert.assertEquals(statusCode, STATUS_CODE_200, "API Execution failed.Status code is not 200"); // Assert status code

        log.info("Response details are as below :");
        log.info("Status code = " + statusCode);
    }

    /**
     * A test method which validates city name from response
     */
    @Test(priority=3,dependsOnMethods ="validateResponseCodeTest" )
    public void validateCityName()
    {
        String actualCityName=WeatherAPIClient.getCityName(jsonResponse);
        Assert.assertEquals(actualCityName,properties.getProperty("cityName"),"API response showing incorrect city name"); //Assert city name
        log.info("City Name = " +actualCityName);
        System.out.println("City Name = " +actualCityName);

    }

    /**
     * A test method which validates count from response
     */
    @Test(priority=2,dependsOnMethods ="validateResponseCodeTest" )
    public void validatecountTest()
    {
        String count=WeatherAPIClient.getcount(jsonResponse);
        Assert.assertEquals(count,properties.getProperty("count"),"API response showing incorrect count"); //Assert count
        log.info("Count =   " +count);
    }

    @AfterTest
    public void cleaup()
    {
        System.out.println("Please refer Result.log for the result of execution");
    }
}