package com.jirapave.imdb.service.impl;

import com.jirapave.imdb.common.constant.ErrorCodes;
import com.jirapave.imdb.common.constant.Settings;
import com.jirapave.imdb.common.exception.RestException;
import com.jirapave.imdb.service.ImdbService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@Service
public class ImdbServiceImpl implements ImdbService {

    private final RestTemplate restTemplate;

    /**
     * URL for title search on imdb.com
     */
    public static final String IMDB_TITLE_URL = "http://www.imdb.com/xml/find?xml=1&nr=1&tt=on&q={name}";


    @Override
    public String searchForTitle(String name) {
        if (name == null || name.isEmpty()){
            throw new RestException(HttpStatus.BAD_REQUEST, ErrorCodes.NAME_NOT_SPECIFIED_ERROR, "Name is empty - please specify name");
        }

        String result;
        try {
            // since we don't know the structure, we will store result XML into String
            result = restTemplate.getForObject(IMDB_TITLE_URL, String.class, new String[]{name});
        } catch (Exception ex){
            throw new RestException(HttpStatus.BAD_REQUEST, ErrorCodes.IMDB_CALL_ERROR, "Error calling imdb, URL: " + IMDB_TITLE_URL + ", given name: " + name);
        }

        String jsonString;
        try {
            // Parsing XML into JSON - result again in String
            JSONObject xmlJSONObj = XML.toJSONObject(result);
            jsonString = xmlJSONObj.toString(Settings.JSON_IDENT);
        } catch (Exception ex){
            throw new RestException(HttpStatus.BAD_REQUEST, ErrorCodes.PARSING_ERROR, "Error parsing XML response from imdb");
        }


        return jsonString;
    }
}
