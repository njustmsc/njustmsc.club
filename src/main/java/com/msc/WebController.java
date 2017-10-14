package com.msc;


import com.msc.mapper.MySQLMapper;
import com.msc.model.Answer1;
import com.msc.model.Answer2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    @ResponseBody
    @RequestMapping(value = "/enroll1",method = RequestMethod.POST,produces = "text/html; charset=UTF-8")
    public String addenroll1(Answer1 answer1,HttpServletResponse response) throws IOException{
        Integer id = mySQLMapper.getNextId();
        mySQLMapper.addBasicInfo(id,answer1.getName(),answer1.getSex(),answer1.getSchool(),answer1.getMail(),answer1.getGrade(),answer1.getPhone(),answer1.getQQ());
        mySQLMapper.addAnswer1(id,answer1.getA21(),answer1.getA22(),answer1.getA23(),answer1.getA24(),answer1.getA31(),answer1.getA32());
        return "<script>parent.alert('提交成功');location='/';</script>";
    }
    @RequestMapping(value = "/enroll2",method = RequestMethod.GET)
    public ModelAndView enroll2(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("answer2",new Answer2());
        modelAndView.setViewName("enroll2");
        return modelAndView;
    }
    @ResponseBody
    @RequestMapping(value = "/enroll2",method = RequestMethod.POST,produces = "text/html; charset=UTF-8")
    public String addenroll2(Answer2 answer2,HttpServletResponse response)throws IOException{
//        ModelAndView modelAndView = new ModelAndView();
        Integer id = mySQLMapper.getNextId();
        if(answer2.getA31() == null || !"true".equals(answer2.getA31())){
            answer2.setA31("false");
        }
        if(answer2.getA32() == null || !"true".equals(answer2.getA32())){
            answer2.setA32("false");
        }
        mySQLMapper.addBasicInfo(id,answer2.getName(),answer2.getSex(),answer2.getSchool(),answer2.getMail(),answer2.getGrade(),answer2.getPhone(),answer2.getQQ());
        mySQLMapper.addAnswer2(id,answer2.getA21(),answer2.getA22(),answer2.getA31(),answer2.getA32(),answer2.getA33(),answer2.getA34());
        return "<script>parent.alert('提交成功');location='/';</script>";

    }
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }
    @RequestMapping("/show")
    public ModelAndView show(){
        ModelAndView modelAndView =new ModelAndView();
        modelAndView.addObject("answer1s", mySQLMapper.queryAllEnroll1());
        modelAndView.addObject("answer2s", mySQLMapper.queryAllEnroll2());

        modelAndView.setViewName("show");

        return modelAndView;
    }
    @RequestMapping("/del")
    public String deluser(HttpServletRequest request){
        mySQLMapper.del(Integer.parseInt(request.getParameter("id")));
        return "redirect:/show";
    }

}
