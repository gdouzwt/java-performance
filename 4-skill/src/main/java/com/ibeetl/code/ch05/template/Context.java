package com.ibeetl.code.ch05.template;

import java.io.IOException;
import java.io.Writer;

/**
 * @author 公众号 java系统优化
 */
public class Context {
  Writer out;
  /**
   * 变量用数组存放不使用map，有着最快的存取速度，而且，连续存储块有利于CPU缓存，提高读写速度
   */

  Object[] args;

  public Context(Writer out, Object[] args){
    this.out = out;
    this.args= args;
  }




  public Writer getWriter(){
    return this.out;
  }

  public Object[] getArgs() {
    return args;
  }

  public void setArgs(Object[] args) {
    this.args = args;
  }

}
