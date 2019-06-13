package com.qf.shop.shop_back.controller;

import com.qf.shop.shop_back.entity.Person;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v2/persons/")
@Api(description = "Set of endpoints for Creating, Retrieving, Updating and Deleting of Persons.")
public class PersonController {


    @RequestMapping(value = "getAllPersons",method = RequestMethod.GET)
    @ApiOperation("Returns list of all Persons in the system.")
    public Person getAllPersons(Person person) {
        System.out.println(person);
        return person;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "用户ID",paramType = "query",dataType = "int", example = "123"),
            @ApiImplicitParam(name = "firstName",value = "用户名称",paramType = "query",dataType = "string", example = "firstName")
    })
    @RequestMapping(value = "getPersons",method = RequestMethod.POST)
    @ApiOperation("getPersons")
    public String getPersons(@RequestParam("id") Integer id, @RequestParam("firstName") String firstName) {
         System.out.println(id + "" + firstName);
        return id + "" + firstName;
    }

    @RequestMapping(value = "getPerson",method = RequestMethod.POST)
    @ApiOperation("getPerson")
    public String getPerson(@RequestParam("id") @ApiParam(value = "id不能为空",example = "123",required = true) Integer id,@RequestParam("firstName") @ApiParam(value = "firstName不能为空",example = "firstName",required = true)String firstName) {
        System.out.println(id + "" + firstName);
        return id + "" + firstName;
    }
}
