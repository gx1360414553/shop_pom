package com.qf.shop.shop_back.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.*;

public class Person {

    @NotNull
    @ApiModelProperty(value = "id",example = "123",position = 0)
    private int id;
    @NotBlank@ApiModelProperty(value="firstName",example = "firstName",position = 2)
    private String firstName;

    @Pattern(regexp = "[SOME REGULAR EXPRESSION]",message = "你輸入的有誤！")
    @Size(min = 1, max = 20)
    @ApiModelProperty(value="lastName",example = "lastName",position = 3)
    private String lastName;
    @Min(0)
    @Max(100)
    @ApiModelProperty(value="age",example = "13",position = 1)
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
