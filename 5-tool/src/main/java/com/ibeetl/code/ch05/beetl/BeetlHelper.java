package com.ibeetl.code.ch05.beetl;

import com.ibeetl.code.ch05.model.User;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.engine.DefaultTemplateEngine;
import org.beetl.core.engine.GrammarCreator;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.core.statement.Expression;
import org.beetl.core.statement.GrammarToken;
import org.beetl.core.statement.Statement;
import org.beetl.core.statement.WhileStatement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 一个模板引擎使用示例
 * @author 公众号 java系统优化
 */
public class BeetlHelper {
  private BeetlHelper(){

  }
  static GroupTemplate gt ;
  static{
    init();
  }

  public static GroupTemplate getGroupTemplate(){
    return gt;
  }

  private  static void init(){
    ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader("templates");
    Configuration cfg = null;
    try {
      cfg = Configuration.defaultConfiguration();
    } catch (IOException e) {
      // 不可能发生
    }
    cfg.setStatementStart("#:");
    cfg.setStatementEnd(null);
    cfg.setPlaceholderStart("${");
    cfg.setPlaceholderEnd("}");


    gt = new GroupTemplate(resourceLoader, cfg);
    //扩展引擎
    gt.setEngine(new MyEngine() );

  }

  private static void changeWhile(){

  }

  public static List<User> samples(){
    List<User> list = new ArrayList<>();
    for(int i=0;i<10;i++){
      User user = new User();
      user.setId(i);
      user.setName("name "+i);
      user.setAge(19+i);
      user.setCreateDate(new Date());
      list.add(user);
    }
    return list;
  }

  static class MyEngine extends DefaultTemplateEngine{
    @Override
    protected GrammarCreator getGrammerCreator(GroupTemplate gt) {
      GrammarCreator grammar = new GrammarCreator(){
        @Override
        public WhileStatement createWhile(Expression exp, Statement whileBody, GrammarToken token) {
          check("While");
          WhileStatement whileStat = new MyWhileStatement(exp, whileBody, token);
          return whileStat;
        }
      };
      return grammar;
    }
  }
}
