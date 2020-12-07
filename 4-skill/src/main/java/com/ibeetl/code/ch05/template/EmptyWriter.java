package com.ibeetl.code.ch05.template;

import java.io.IOException;
import java.io.Writer;
/**
 * 实现{@code Writer} ,模拟一个空的输出，以正确验证模板渲染性能，这在很多序列化，IO相关工具性能测试中非常重要
 * 数据库驱动的性能测试则是实现Connection和PreparedStatment，一个空的实现
 * @author 公众号 java系统优化
 *
 */
public class EmptyWriter extends Writer {

  public void write(String str, int off, int len){

  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {

  }

  @Override
  public void flush() throws IOException {

  }

  @Override
  public void close() throws IOException {

  }
}
