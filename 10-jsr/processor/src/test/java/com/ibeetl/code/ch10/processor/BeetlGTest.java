package com.ibeetl.code.ch10.processor;

import java.io.IOException;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;

public class BeetlGTest {

    public static void main(String[] args) {
        //初始化代码
        ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader();
        Configuration cfg = null;
        try {
            cfg = Configuration.defaultConfiguration();
            cfg.setStatementStart("@");
            cfg.setStatementEnd(null);
        } catch (IOException e) {
            return;
        }

        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
        //获取模板
        Template t = gt.getTemplate("mapper.btl");
        if (t == null) {
            System.out.println("获取beetl模板文件错误！");
            return;
        }
        t.binding("packageName", "com.ibeetl.com.ch12");
        t.binding("interfaceName", "UserJsonMapper");
        t.binding("generateClassSimpleName", "Jsr269_UserJsonMapper");
        t.binding("writeMethodName", "write");
        t.binding("parameterTypeQualifiedName", "com.ibeetl.com.ch12.User");
        t.binding("parameterTypeName", "User");
        t.binding("parameterName", "user");
        t.binding("varsInTypeForParameter", null);
        //渲染结果
        String str = t.render();
        System.out.println(str);
    }
}
