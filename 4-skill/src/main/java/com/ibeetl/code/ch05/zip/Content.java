package com.ibeetl.code.ch05.zip;

import java.io.IOException;
import java.io.InputStream;

public class Content {
    byte[] bs = null;

    public Content(){

    }

    private byte[] loadContent(int expected) throws IOException {
      InputStream ins = this.getClass().getResourceAsStream("/content.md");
      int size = ins.available();
      if(expected>size){
        throw new IllegalArgumentException("expected "+expected+" size "+size);
      }
      bs = new byte[expected];
      ins.read(bs);
      return bs;


    }
    public  byte[] genContentBySize(int size){
      try{
        return loadContent(size);
      }catch(IOException e){
        throw new IllegalStateException(e);
      }

    }
}
