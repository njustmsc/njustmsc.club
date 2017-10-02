package com.msc.mapper;

import com.msc.model.Answer1;
import com.msc.model.Answer2;
import com.msc.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MySQLMapper {
    @Select("select * from user where username=#{username}")
    @Results(value={
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "password",property = "password")
    })
    User queryUserByUsername(@Param("username")String username);
    @Insert("insert into basicinfo(id,name,sex,birthday,mail,grade,phone,QQ) " +
            "values(#{id},#{name},#{sex},#{birthday},#{mail},#{grade},#{phone},#{QQ})")
    void addBasicInfo(@Param("id")Integer id,@Param("name")String name,@Param("sex")String sex,@Param("birthday")String birthday,
                      @Param("mail")String mail,@Param("grade")String grade,@Param("phone")String phone,@Param("QQ")String QQ);
    @Insert("insert into answer1(basicinfoid,a21,a22,a23,a24,a31,a32)" +
            "values(#{id},#{a21},#{a22},#{a23},#{a24},#{a31},#{a32})")
    void addAnswer1(@Param("id")Integer basicinfoId,@Param("a21")String a21,@Param("a22")String a22,@Param("a23")String a23,@Param("a24")String a24,@Param("a31")String a31,@Param("a32")String a32);

    @Insert("insert into answer2(basicinfoid,a21,a22,a31,a32,a33,a34)" +
            "values(#{id},#{a21},#{a22},#{a31},#{a32},#{a33},#{a34})")
    void addAnswer2(@Param("id")Integer basicinfoId,@Param("a21")String a21,@Param("a22")String a22,@Param("a31")String a31,@Param("a32")String a32,@Param("a33")String a33,@Param("a34")String a34);


    @Select("SELECT auto_increment FROM information_schema.`TABLES` WHERE  TABLE_NAME='basicinfo'")
    @Results(value = {
            @Result(column = "auto_increment",property = "")
    })
    Integer getNextId();

    @Select("SELECT * FROM msc.basicinfo inner join msc.answer2 where msc.basicinfo.id = msc.answer2.basicinfoid")
    @Results(value = {
            @Result(id=true,column = "id",property = "id"),
            @Result(id=true,column = "name",property = "name"),
            @Result(id=true,column = "sex",property = "sex"),
            @Result(id=true,column = "birthday",property = "birthday"),
            @Result(id=true,column = "mail",property = "mail"),
            @Result(id=true,column = "grade",property = "grade"),
            @Result(id=true,column = "phone",property = "phone"),
            @Result(id=true,column = "QQ",property = "QQ"),
            @Result(id=true,column = "a21",property = "a21"),
            @Result(id=true,column = "a22",property = "a22"),
            @Result(id=true,column = "a23",property = "a23"),
            @Result(id=true,column = "a31",property = "a31"),
            @Result(id=true,column = "a32",property = "a32"),
            @Result(id=true,column = "a33",property = "a33"),
            @Result(id=true,column = "a34",property = "a34"),

    })
    List<Answer2> queryAllEnroll2();

    @Select("SELECT * FROM msc.basicinfo inner join msc.answer1 where msc.basicinfo.id = msc.answer1.basicinfoid")
    @Results(value = {
            @Result(id=true,column = "id",property = "id"),
            @Result(id=true,column = "name",property = "name"),
            @Result(id=true,column = "sex",property = "sex"),
            @Result(id=true,column = "birthday",property = "birthday"),
            @Result(id=true,column = "mail",property = "mail"),
            @Result(id=true,column = "grade",property = "grade"),
            @Result(id=true,column = "phone",property = "phone"),
            @Result(id=true,column = "QQ",property = "QQ"),
            @Result(id=true,column = "a21",property = "a21"),
            @Result(id=true,column = "a22",property = "a22"),
            @Result(id=true,column = "a23",property = "a23"),
            @Result(id=true,column = "a24",property = "a24"),
            @Result(id=true,column = "a31",property = "a31"),
            @Result(id=true,column = "a32",property = "a32"),
    })
    List<Answer1> queryAllEnroll1();

    @Delete("delete from basicinfo where id = #{id}")
    void del(@Param("id")Integer id);
}
