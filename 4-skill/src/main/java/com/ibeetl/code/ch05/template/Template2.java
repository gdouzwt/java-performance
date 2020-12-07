package com.ibeetl.code.ch05.template;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Template2 {

    List<Token> tokens;
    static  EmptyToken emptyToken = new  EmptyToken();

    public Template2(List<Token> tokens){

      this.tokens = tokens;
      int size = tokens.size();
      int mode = 4-size%4;
      for(int i=0;i<mode;i++){
          tokens.add(emptyToken);
      }

    }

    public final String render(Writer out,Object... args) throws IOException {

        Context ctx = new Context(out,args);
        List<Token> locals = tokens;
        int size = locals.size();
        for(int i=0;i<size;){
            locals.get(i++).render(ctx);
            locals.get(i++).render(ctx);
            locals.get(i++).render(ctx);
            locals.get(i++).render(ctx);
        }

        return out.toString();
    }

}
