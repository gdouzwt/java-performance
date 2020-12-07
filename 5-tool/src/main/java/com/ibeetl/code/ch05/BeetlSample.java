package com.ibeetl.code.ch05;

import com.ibeetl.code.ch05.model.User;
import com.ibeetl.code.ch05.beetl.BeetlHelper;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Script;
import org.beetl.core.Template;
import org.beetl.core.resource.StringTemplateResourceLoader;

import java.util.Arrays;
import java.util.List;

public class BeetlSample {
  public static  void main(String[] args){
//    hello();
//    runScript();
//    testErrorTip();
    testWhile();
  }

  public static void hello(){
    GroupTemplate gt = BeetlHelper.getGroupTemplate();
    Template t = gt.getTemplate("/hello.html");
    List<User> users = BeetlHelper.samples();
    t.binding("users",users);
    String output = t.render();
    System.out.println(output);
  }

  public  static void runScript(){
    GroupTemplate gt = BeetlHelper.getGroupTemplate();
    StringTemplateResourceLoader resourceLoader = new StringTemplateResourceLoader();
    {
      String scriptStr = "var 年薪=工资*14;";
      Script script = gt.getScript(scriptStr,resourceLoader);
      script.binding("工资",800);;
      script.execute();
      Long salary  = ((Integer)script.getVar("年薪")).longValue();
      System.out.println(salary);
    }

    {
      List ages = Arrays.asList(12,46,34);
      String scriptStr = "var total = 0;" +
        "for(age in ages){ " +
        "   total+=age;" +
        "} " +
        "return total;";

      Script script = gt.getScript(scriptStr,resourceLoader);
      script.binding("ages",ages);;
      script.execute();

      Integer total  = (Integer)script.getReturnValue();
      System.out.println(total);
    }


  }

  public static  void testErrorTip(){
    GroupTemplate gt = BeetlHelper.getGroupTemplate();
    Template t = gt.getTemplate("/error.html");
    String output = t.render();
    System.out.println(output);

    t = gt.getTemplate("/error2.html");
    output = t.render();
    System.out.println(output);
  }


  public static  void testWhile(){
    //模拟一个无限循环
    GroupTemplate gt = BeetlHelper.getGroupTemplate();
    Template t = gt.getTemplate("/while.html");
    String output = t.render();
    System.out.println(output);

  }

}


