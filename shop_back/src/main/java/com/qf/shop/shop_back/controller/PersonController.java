package com.qf.shop.shop_back.controller;

import com.qf.shop.shop_back.entity.Person;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/persons/")
@Api(description = "Set of endpoints for Creating, Retrieving, Updating and Deleting of Persons.")
public class PersonController {


    @RequestMapping(value = "getAllPersons",method = RequestMethod.GET, produces = "application/json")
    @ApiOperation("Returns list of all Persons in the system.")
    public Person getAllPersons(Person person) {
        System.out.println(person);
        return person;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "用户ID",paramType = "path",dataType = "int", example = "123"),
            @ApiImplicitParam(name = "firstName",value = "用户名称",paramType = "form",dataType = "string", example = "firstName")
    })
    @RequestMapping(value = "getPersons",method = RequestMethod.GET, produces = "application/json")
    @ApiOperation("getPersons")
    public String getPersons(int id,String firstName) {
         System.out.println(id + "" + firstName);
        return "ok";
    }
}
