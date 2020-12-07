package com.ibeetl.code.ch05.model;

import java.util.List;

public class Department  implements  java.io.Serializable{
  Integer id;
  String name;
  List<User> users;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }
}
