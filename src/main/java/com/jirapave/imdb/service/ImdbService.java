package com.jirapave.imdb.service;

public interface ImdbService {

    /**
     * Call imdb.com for all information about the given title/name.
     * @param name Name of the title
     * @return All information about given title/name in JSON format as a String
     */
    String searchForTitle(String name);

}
