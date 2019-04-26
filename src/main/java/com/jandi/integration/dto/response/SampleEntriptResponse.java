package com.jandi.integration.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SampleEntriptResponse {

    @ApiModelProperty(notes = "for header authorization value")
    private String authorization;

    @ApiModelProperty(notes = "for header date Value",example="Thu, 25 Apr 2019 03:14:02 GMT")
    private String date;

    @ApiModelProperty(notes = "method",example="GET|POST|PUT|DELETE")
    private String verb;

    @ApiModelProperty(notes = "path")
    private String path;

}
