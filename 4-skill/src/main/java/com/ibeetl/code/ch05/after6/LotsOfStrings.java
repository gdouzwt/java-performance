package com.ibeetl.code.ch05.after6;

import java.util.LinkedList;

/**
 * 使用虚拟机开关，可以有效节约缓存，同样内容的String(char[])将会复用一个char[]
 * @author 公众号 java系统优化
 */
public class LotsOfStrings {

  private static final LinkedList<String> LOTS_OF_STRINGS = new LinkedList<>();

  public static void main(String[] args) throws Exception {
    int iteration = 0;
    while (true) {
      for (int i = 0; i < 100; i++) {
        for (int j = 0; j < 1000; j++) {
          LOTS_OF_STRINGS.add(new String("String " + j));
        }
      }
      iteration++;
      System.out.println("Survived Iteration: " + iteration);
      Thread.sleep(100);
    }
  }
}
