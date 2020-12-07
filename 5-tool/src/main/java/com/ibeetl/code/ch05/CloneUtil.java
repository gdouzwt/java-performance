package com.ibeetl.code.ch05;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibeetl.code.ch05.model.Department;
import com.ibeetl.code.ch05.model.User;
import fr.xebia.extras.selma.Selma;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** 各种克隆方法，从jvm缓存中取出的对象需要克隆才能任意使用
 * 那种克隆方法快，维护简单？
 * @author 公众号 java系统优化
 */
public class CloneUtil {

  DepartmentMapper mapper = Selma.builder(DepartmentMapper.class).build();
  ObjectMapper jsonMapper = new ObjectMapper();

  public  Department cloneDepartment(Department source){
    Department target = new Department();
    target.setId(source.getId());
    target.setName(source.getName());
    List<User> users = source.getUsers();
    if(users!=null){
      List userList = new ArrayList(users.size());
      for(User user:users){
        User targetUser= cloneUser(user);
        userList.add(targetUser);
      }
      target.setUsers(userList);
    }else{
      target.setUsers(null);
    }

    return target;

  }

   public User cloneUser(User source){
    if(source==null){
      return null;
    }
    User target = new User();
    target.setId(source.getId());
    target.setName(source.getName());
    target.setPassword(source.getPassword());
    target.setAge(source.getAge());
    if(source.getCreateDate()!=null){
      target.setCreateDate(new Date(source.getCreateDate().getTime()));
    }else{
      target.setCreateDate(null);
    }

    return target;
  }

  public Department cloneByObjectWriter(Department source){
    try {
      ByteArrayOutputStream  bos = new ByteArrayOutputStream();
      ObjectOutputStream os = new ObjectOutputStream(bos);
      os.writeObject(source);
      os.close();
      ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
      ObjectInputStream is = new ObjectInputStream(bis);
      Department department = (Department)is.readObject();
      return department;
    } catch (IOException | ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  public Department cloneBySelma(Department source){

    return mapper.clone(source);
  }

	public Department cloneByJson(Department source){
		try{
			String str =  jsonMapper.writeValueAsString(source);
			Department newDepartment = jsonMapper.readValue(str,Department.class);
			return newDepartment;
		}catch(Exception ex){
			throw new RuntimeException(ex);
		}

	}




}
