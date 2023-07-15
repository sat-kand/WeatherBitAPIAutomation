package com.qa.APIDefinitions;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;

import com.qa.Base.TestBase;
import com.qa.Utils.Utility;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/**
 *
 * @author Sat Kandhaswami WeatherAPIClient acts as client responsible for executing
 *         the weather API using Get method. It also parses and prints the
 *         response
 *
 */
public class WeatherAPIClient extends TestBase {

    public static Response response;
    public static JsonPath jsonPath;

    /**
     * This method is responsible for executing the weather api using GET method
     *
     * @return response in raw format
     */

    public static Response executeWeatherAPI() {
        intialization();


        try {
            response = given().spec(Utility.generateRequestSpecification()).when()
                    .get(properties.getProperty("onecalluri")).then().extract().response();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return response;

    }

    /**
     * This method is responsible for executing the weather api using GET method
     *
     * @return response in raw format
     */

    public static Response executeLatLongWeatherAPI() {
        intialization();


        try {
            response = given().spec(Utility.generateLatLongRequestURI()).when()
                    .get(properties.getProperty("onecalluri")).then().extract().response();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return response;

    }

    /**
     * This methods converts raw response in Json format
     *
     * @param rawResponse :Raw response generated when GET method is executed in
     *                    executeWeatherAPI()
     * @return response in Json format
     */
    public static JsonPath getJsonResponse(Response rawResponse) {

        jsonPath = Utility.convertToJsonResponse(rawResponse);
        return jsonPath;
    }

    /**
     * This method fetches latitude from Json response
     *
     * @param jsonResponse Response in Json format
     * @return latitude from Json response
     */
    public static String getLatitude(JsonPath jsonResponse) {

        String actualLatitude = Utility.getValueFromJsonResponse(jsonResponse, "lat");
        return actualLatitude;

    }

    /**
     * This method fetches longitude from Json response
     *
     * @param jsonResponse Response in Json format
     * @return longitude from Json response
     */
    public static String getLongitude(JsonPath jsonResponse) {

        String actualLongitude = Utility.getValueFromJsonResponse(jsonResponse, "lon");
        return actualLongitude;
    }

    /**
     * This method fetches timezone from Json response
     *
     * @param jsonResponse Response in Json format
     * @return timezone from Json response
     */
    public static String getCityName(JsonPath jsonResponse) {

        String actualCityName = Utility.getValueFromJsonResponse(jsonResponse, "data[0].city_name");
        return actualCityName;
    }

    /**
     * This method fetches timezone from Json response
     *
     * @param jsonResponse Response in Json format
     * @return timezone from Json response
     */
    public static String getcount(JsonPath jsonResponse) {

        String count = Utility.getValueFromJsonResponse(jsonResponse, "count");
        return count;

    }


    /**
     * This method fetches response code from raw response
     *
     * @param rawResponse Response in raw format
     * @return status code of API execution
     */
    public static int getResponseCode(Response rawResponse) {

        int statusCode = rawResponse.getStatusCode();
        return statusCode;
    }

}
