package com.ibeetl.code.ch02.tree;

import java.util.HashMap;
import java.util.Map;

public class Node {

  //子节点
  private Map<Character, Node> nextNodes = new HashMap<>();

  public void addNextNode(Character key, Node node){
    nextNodes.put(key,node);
  }

  public Node getNextNode(Character key){
    return nextNodes.get(key);
  }

  public boolean isLastCharacter(){
    return nextNodes.isEmpty();
  }

}
