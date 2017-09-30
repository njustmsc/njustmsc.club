package com.msc;

import com.msc.model.AjaxRequest;
import com.msc.model.AjaxResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {
    @RequestMapping("login")
    public AjaxResponse login(@RequestBody AjaxRequest ajaxRequest){
        return new AjaxResponse();
    }
}
