package com.ibeetl.code.ch05.beetl;

import org.beetl.core.Context;
import org.beetl.core.exception.BeetlException;
import org.beetl.core.statement.*;
/**
 * 设置循环次数不超过一定次数
 * @author 公众号 java系统优化
 */
public class MyWhileStatement extends WhileStatement {

  public MyWhileStatement(Expression exp, Statement whileBody, GrammarToken token) {
    super(exp, whileBody, token);
  }

  @Override
  public void execute(Context ctx) {
    Counter counter = new Counter();
    while (true) {
      if(!counter.add().isAllow()){
        BeetlException be = new BeetlException(BeetlException.ERROR,"DEMO版本限制 循环次数不超过"+counter.max+"次");
        be.pushToken(exp.token);
        throw be;
      }
      Object result = exp.evaluate(ctx);

      if (result instanceof Boolean) {
        if ((Boolean) result) {
          whileBody.execute(ctx);
          switch (ctx.gotoFlag) {
            case IGoto.NORMAL:
              break;
            case IGoto.CONTINUE:
              ctx.gotoFlag = IGoto.NORMAL;
              continue;
            case IGoto.RETURN:
              return;
            case IGoto.BREAK:
              ctx.gotoFlag = IGoto.NORMAL;
              return;
          }
        } else {
          break;
        }

      } else {
        BeetlException be = new BeetlException(BeetlException.BOOLEAN_EXPECTED_ERROR);
        be.pushToken(exp.token);
        throw be;
      }

    }
  }

  static class Counter{
    int c = 0;
    int max = 3;
    public Counter add(){
      c++;
      return this;
    }
    public boolean isAllow(){
      return !(c>max);
    }
  }


}
