package com.ibeetl.code.ch01.com.ibeetl.code.ch01.jmh;

import org.junit.Assert;
import org.junit.Test;

/**
 * 所有的jmh测试，都应该写一个单元测试，保证功能正确先
 * @author 公众号 java系统优化
 */
public class InLineTestJunit {
  @Test
  public void test(){
    Inline inline = new Inline();
    inline.init();
    int expectd = inline.x+inline.y;
    int ret = inline.add();
    int ret2 = inline.addInline();
    Assert.assertEquals(expectd,ret);
    Assert.assertEquals(expectd,ret2);
  }
}
