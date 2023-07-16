package com.qa.framework;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/**
 *
 * @author Sat Kandhaswami This class provides various utility methods
 *
 */
public class Utility extends TestBase {

    /**
     * This method converts Raw response into Json format
     *
     * @param rawResponse This is Raw response generated by Weather API Client
     * @return returns response in Json format
     */
    public static JsonPath convertToJsonResponse(Response rawResponse) {

        String responseString = rawResponse.asString();
        JsonPath responseJson = new JsonPath(responseString);
        return responseJson;
    }

    /**
     * This method fetches value of any key from Json response
     *
     * @param jsonResponse Response in Json format, key required key in string format
     * @return key's value as string
     */
    public static String getValue(JsonPath jsonResponse, String key) {

        Object value = jsonResponse.get(key);
        return value.toString();
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
