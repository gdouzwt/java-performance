package com.ibeetl.code.ch02.tree;

public class KeywordSearch {

	Node root = new Node();
	String sensitiveWords = "***";

	public void addWord(String word) {

		Node tempNode = root;
		for (int i = 0; i < word.length(); i++) {
			Character c = word.charAt(i);
			Node node = tempNode.getNextNode(c);
			if (node == null) {
				node = new Node();
				tempNode.addNextNode(c, node);
			}
			// 移动到下一个字
			tempNode = node;


		}
	}

	public String filter(String text) {
		StringBuilder result = new StringBuilder(text.length());
		Node tempNode = root;
		int begin = 0;
		int position = 0;
		while (position < text.length()) {

			Character c = text.charAt(position);
			tempNode = tempNode.getNextNode(c);

			if (tempNode == null) {
				////如果匹配失败，合法
				result.append(text.charAt(begin));
				begin = begin + 1;
				position = begin;
				//从新匹配
				tempNode = root;
				continue;
			} else if (tempNode.isLastCharacter()) {
				//匹配结束，替换敏感词
				result.append(sensitiveWords);
				position++;
				begin = position;
				tempNode = root;
			} else {
				position++;
			}

		}
		//添加剩下的内容
		result.append(text.substring(begin));
		return result.toString();
	}

	public static void main(String[] args) {
		KeywordSearch ts = new KeywordSearch();
		ts.addWord("猪狗");
		ts.addWord("小猫");
		ts.addWord("天气预报");

		String ret = ts.filter("你好，小猫");
		System.out.println(ret);

	}


}



