package com.ibeetl.code.ch05.template;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Template {

    List<Token> tokens;

    public Template(List<Token> tokens){
      this.tokens = tokens;
    }

    public final String render(Writer out,Object... args) throws IOException {
        Context ctx = new Context(out,args);
        List<Token> locals = tokens;
        int size = locals.size();
        for(int i=0;i<size;i++){
            Token token = locals.get(i);
            token.render(ctx);
        }
        return out.toString();
    }


    static Map<String,Template > cache = new ConcurrentHashMap<>();

    public static Template getTemplate(String pattern){
      Template template = cache.get(pattern);
      if(template!=null){
        return template;
      }

      template = parse(pattern);
      cache.put(pattern,template);
      return template;
    }

    protected static Template parse(String pattern){

      int index = -1;
      int start = 0;
      int varIndex = 0;
      List<Token> list = new ArrayList<>();
      while ((index = pattern.indexOf("{}", start)) != -1) {
        String text = pattern.substring(start, index);
        Token textToken= new StaticTextToken(text);
        start = index + 2;
        VarToken varToken = new VarToken(varIndex++);
        list.add(textToken);
        list.add(varToken);
      }

      if (start < pattern.length()) {
        String text = pattern.substring(start);
        Token textToken= new StaticTextToken(text);
        list.add(textToken);
      }
      Template template = new Template(list);
      return template;
    }

    public List<Token> getTokens() {
        return tokens;
    }
}
