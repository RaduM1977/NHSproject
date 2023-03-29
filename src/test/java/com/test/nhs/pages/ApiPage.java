package com.test.nhs.pages;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

import java.util.*;

public class ApiPage {
    Response response;
    public Response getResponse(String Url){
        response = RestAssured.
                given().
                header("Cookie","connect.sid=s%3AGbV5xQbdZrAAfCpk6C8d1iJLZPP9rn3J.FAyEjM8pbc2Gj7LJBtPOuyBnRVe%2B3h7Wwxqdl6o1po8").
                get(Url).
                then().//log().body().
                extract().response();

        return response;
    }

    public Map<String,Object> deserializeResponseMap(){
        Map<String,Object> map = response.as(new TypeRef<Map<String, Object>>() {});

//        Set<String> expectedNamesSet = map.keySet();
//        List<String> expectedNamesList = new ArrayList<>(expectedNamesSet);
        map.remove("noroom");
         return map;
    }

    public List<Map<String, String>> deserializeResponseListOfMaps(){
        List<Map<String,Object>> listOfMaps = response.as(new TypeRef<List<Map<String, Object>>>() {});

        List<Map<String,String>> updatedListOfMaps = new ArrayList<>();

        for(Map<String,Object> map:listOfMaps){
            Map<String,String> updatedMap = new LinkedHashMap<>();

            String hospitalNumberValue = String.valueOf(map.get("hospitalNumber")).trim();
            updatedMap.put("hospitalNumber", hospitalNumberValue);

            String firstNameValue = String.valueOf(map.get("firstName")).trim();
            updatedMap.put("firstName",firstNameValue) ;

            String lastNameValue = String.valueOf(map.get("lastName")).trim();
            updatedMap.put("lastName", lastNameValue);

            String roomValue = String.valueOf(map.get("room")).trim();
            updatedMap.put("room", roomValue);

            String scoreValue = String.valueOf(map.get("score")).trim();
            updatedMap.put("score", scoreValue);

            updatedListOfMaps.add(updatedMap);
        }
        updatedListOfMaps.sort(Comparator.comparing(map-> map.get("hospitalNumber")));

        return updatedListOfMaps;
    }

    public List<String> keysList(){
        Set<String> diseasesNamesSet = deserializeResponseMap().keySet();
        return new ArrayList<>(diseasesNamesSet);
    }

    public List<Object> valueList(){
        Collection<Object> expectedScoreList = deserializeResponseMap().values();
        return new ArrayList<>(expectedScoreList);
    }


    public int getStatusCode(){
        return response.statusCode();
    }

}
