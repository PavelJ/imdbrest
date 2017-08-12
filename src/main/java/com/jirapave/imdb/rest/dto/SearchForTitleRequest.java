package com.jirapave.imdb.rest.dto;

import com.jirapave.imdb.rest.dto.base.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchForTitleRequest extends BaseRequest {

    /**
     * Name of the title on imdb.com
     */
    private String title;

}
