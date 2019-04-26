package com.jandi.integration.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class SampleEntriptRequest {

    @ApiModelProperty(example="Hello Jandi World")
    private String message ;

}
