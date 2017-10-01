package com.msc;


import com.msc.mapper.MySQLMapper;
import com.msc.model.Answer1;
import com.msc.model.Answer2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class WebController {
    @Autowired
    private MySQLMapper mySQLMapper;
    @RequestMapping(value = {"/","index"})
    public ModelAndView main(){
        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("signup",new Signup());
        modelAndView.setViewName("index");
        return modelAndView;
    }
    @RequestMapping(value = "/enroll1",method = RequestMethod.GET)
    public ModelAndView enroll1(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("answer1",new Answer1());
        modelAndView.setViewName("enroll1");
        return modelAndView;
    }
    @RequestMapping(value = "enroll1",method = RequestMethod.POST)
    public String addenroll1(Answer1 answer1,HttpServletResponse response) throws IOException{
//        ModelAndView modelAndView = new ModelAndView();
        Integer id = mySQLMapper.getNextId();
        mySQLMapper.addBasicInfo(id,answer1.getName(),answer1.getSex(),answer1.getBirthday(),answer1.getMail(),answer1.getGrade(),answer1.getPhone(),answer1.getQQ());
        mySQLMapper.addAnswer1(id,answer1.getA21(),answer1.getA22(),answer1.getA23(),answer1.getA31(),answer1.getA32());


        response.setContentType("text/html; charset=utf-8");
        PrintWriter out;
        out = response.getWriter();
        out.print("<script>alert('提交成功!');</script>");
        out.print("<script>location='/';</script>");
        out.flush();
        return "";
    }
    @RequestMapping(value = "/enroll2",method = RequestMethod.GET)
    public ModelAndView enroll2(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("answer2",new Answer2());
        modelAndView.setViewName("enroll2");
        return modelAndView;
    }
    @RequestMapping(value = "enroll2",method = RequestMethod.POST)
    public String addenroll2(Answer2 answer2,HttpServletResponse response)throws IOException{
//        ModelAndView modelAndView = new ModelAndView();
        Integer id = mySQLMapper.getNextId();
        if(answer2.getA31() == null || !"true".equals(answer2.getA31())){
            answer2.setA31("false");
        }
        mySQLMapper.addBasicInfo(id,answer2.getName(),answer2.getSex(),answer2.getBirthday(),answer2.getMail(),answer2.getGrade(),answer2.getPhone(),answer2.getQQ());
        mySQLMapper.addAnswer2(id,answer2.getA21(),answer2.getA22(),answer2.getA31(),answer2.getA32(),answer2.getA33());

        response.setContentType("text/html; charset=utf-8");
        PrintWriter out;
        out = response.getWriter();
        out.print("<script>alert('提交成功!');</script>");
        out.print("<script>location='/';</script>");
        out.flush();
        return "";

    }
    @RequestMapping("/show")
    public ModelAndView show(){
        ModelAndView modelAndView =new ModelAndView();
        modelAndView.addObject("answer1s", mySQLMapper.queryAllEnroll1());
        modelAndView.addObject("answer2s", mySQLMapper.queryAllEnroll2());

        modelAndView.setViewName("show");

        return modelAndView;
    }

}
