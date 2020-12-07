package com.ibeetl.code.ch05.model;

import java.util.Date;
public class User implements  java.io.Serializable{
  Integer id;
  Integer age;
  String password;
  String name;
  Date createDate;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  @Override
  public String toString() {
    return "User{" +
      "id=" + id +
      ", age=" + age +
      ", password='" + password + '\'' +
      ", name='" + name + '\'' +
      ", createDate=" + createDate +
      '}';
  }
}
