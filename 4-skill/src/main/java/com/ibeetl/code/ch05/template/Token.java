package com.ibeetl.code.ch05.template;

import java.io.IOException;
/**
 * 语法解析的抽象
 * @author 公众号 java系统优化
 * @see StaticTextToken
 * @See VarToken
 */
public interface Token {
  public void render(Context ctx) throws IOException;
}
