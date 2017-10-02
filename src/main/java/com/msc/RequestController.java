package com.msc;

import com.msc.mapper.MySQLMapper;
import com.msc.model.AjaxRequest;
import com.msc.model.AjaxResponse;
import com.msc.model.Answer1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
public class RequestController {
    @Autowired
    private MySQLMapper mySQLMapper;
    @RequestMapping("login")
    public AjaxResponse login(@RequestBody AjaxRequest ajaxRequest){
        return new AjaxResponse();
    }

}
