package com.jirapave.imdb.rest.controller;

import com.jirapave.imdb.common.annotation.LogController;
import com.jirapave.imdb.rest.dto.SearchForTitleRequest;
import com.jirapave.imdb.service.ImdbService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "Endpoint for search session listing")
@RestController
@RequestMapping("/imdb")
@Validated
@Slf4j
@RequiredArgsConstructor
@LogController
public class ImdbController {

    private final ImdbService imdbService;

    @PostMapping(value = "/title", produces = "application/json")
    @ApiOperation(value = "Find information on imdb.com about a title", notes = "Find information on imdb.com about a title based on a given search criteria")
    public String searchForTitle(@RequestBody SearchForTitleRequest request) {
        /**
         * Two main possible solutions here:
         * - Create DTO for XML response of imdb, map imdb response to this DTO and than just return the same DTO but using jackson parser, this is standard way
         * when we have some definition of incoming format
         *  - If don't have definition of incoming format and we want just to translate XML to JSON without "understanding" it, we can just transform it by hand
         *  using json library and send the result
         */
        return imdbService.searchForTitle(request.getTitle());
    }

}
