package com.jandi.integration.controller;

import com.jandi.integration.dto.response.SampleEntriptResponse;
import com.jandi.integration.hmac.Hmac;
import com.jandi.integration.hmac.HmacUtil;
import com.jandi.integration.util.DateTimeUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/sample")
@Api(value="Sample Group", description="sample api" ,tags = "Sample")
public class SampleController {

    @ApiOperation(value = "Echo Api",position = 2)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", required = true, allowEmptyValue = false, paramType = "header", dataType="string"),
        @ApiImplicitParam(name = "X-Date",  required = true, allowEmptyValue = false, paramType = "header", dataType="string")
    })
    @PostMapping(value = "/echo" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes= MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity echo(
            @ApiParam(value = "Data to be stored in Json format", required = true, type = "array", examples = @Example(value = {
                    @ExampleProperty(value = "{\"message\":\"Hello Jandi World\"}", mediaType = "application/json")
            }))
            @RequestBody Object body) {

        return new ResponseEntity(body, HttpStatus.OK);
    }
    @ApiOperation(value = "Encript Api",response = SampleEntriptResponse.class,position = 1)
    @PostMapping(value = "/encript", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes= MediaType.APPLICATION_JSON_UTF8_VALUE )
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity encode(
            @RequestParam(value = "accessKeyId", required = true ,defaultValue="") String accessKeyId,
            @RequestParam(value = "verb", required = true ,defaultValue="POST") String verb,
            @RequestParam(value = "path", required = true ,defaultValue="/sample/echo") String path,
            @RequestBody(required = true) String body) throws Exception{

        byte[] payload = body.getBytes();
        String date =  DateTimeUtil.toGMTFormat(DateTimeUtil.nowGMT());
        String signature = HmacUtil.createSignature(Hmac.SECURE_ALGORITHM,Hmac.SECURE_KEY,verb,path,payload,date);
        String authorization =String.format("%s %s:%s","JANDI-HMAC-SHA256",accessKeyId,signature);

        return new ResponseEntity(SampleEntriptResponse.builder()
                        .authorization(authorization)
                        .date(date)
                        .path(path)
                        .verb(verb)
                        .build()
                    ,HttpStatus.OK);
    }

}
