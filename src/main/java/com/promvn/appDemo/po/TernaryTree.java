package com.promvn.appDemo.po;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;

@Data
@NoArgsConstructor

//定义节点
class Node {
    //存储字符串
    char storeChar;
    //是否完成单词
    boolean isComplete;

    Node leftChild,centerChild,rightChild;

    //构造方法
    public Node(char storeChar,boolean isComplete) {
        this.storeChar = storeChar;
        this.isComplete = isComplete;
    }
}

public class TernaryTree {
    //根节点
    public Node root;

    //存储结果


    //递归创建tree
    public Node insert(String word, Node node, Integer index) {
        if(word == null || "".equals(word))
            return null;

        //将word转成char数组
        char[] charArray = word.toCharArray();

        //递归终止条件，当没有改字符时，创建新节点
        if(node == null) {
            node = new Node(charArray[index],false);
        }

        if(charArray[index] < node.storeChar) {
            node.leftChild = this.insert(word, node.leftChild,index);
        } else if(charArray[index] > node.storeChar) {
            node.rightChild = this.insert(word, node.rightChild,index);
        } else {
            //如果为word最后一个字符，那么设置为单词完结,如为最后一个字符，必定进入这一步
            if(index + 1 == charArray.length) {
                node.isComplete = true;
            } else {
                node.centerChild = this.insert(word, node.centerChild,++index);
            }
        }

        return node;
    }

    //封装
    public void insert(String word) {
        root = this.insert(word,root,0);
    }


    public String toString()
    {
        HashSet<String> result = new HashSet<String>();
        traverse(root, "", result);
        return "\nTernary Search Tree : "+ result;
    }
    //遍历
    private void traverse(Node node, String str, HashSet<String> result)
    {
        if (node != null)
        {
            traverse(node.leftChild, str, result);

            str = str + node.storeChar;
            if (node.isComplete)
                result.add(str);

            traverse(node.centerChild, str, result);
            str = str.substring(0, str.length() - 1);

            traverse(node.rightChild, str, result);
        }
    }

    public boolean search(String word)
    {
        return search(root, word.toCharArray(), 0);
    }

    private boolean search(Node node, char[] word, int index)
    {
        if (node == null)
            return false;

        if (word[index] < node.storeChar)
            return search(node.leftChild, word, index);
        else if (word[index] > node.storeChar)
            return search(node.rightChild, word, index);
        else
        {
            if (node.isComplete && index == word.length - 1)
                return true;
            else if (index == word.length - 1)
                return false;
            else
                return search(node.centerChild, word, index + 1);
        }
    }

    public Node findNode(String prefix) {
        return findNode(root,prefix.toCharArray(),0);
    }

    public Node findNode(Node node, char[] word, int index) {
        if (node == null)
            return null;

        if (word[index] < node.storeChar)
            return findNode(node.leftChild, word, index);
        else if (word[index] > node.storeChar)
            return findNode(node.rightChild, word, index);
        else
        {
            if (index == word.length - 1)
                return node.centerChild;
            else
                return findNode(node.centerChild, word, index + 1);
        }
    }

    public HashSet<String> preSearch(String prefix){
        HashSet<String> result = new HashSet<String>();
        Node node = findNode(prefix);
        prefixSearch(prefix, node, result);
        return result;
    }

    //查找前缀相同的word
    public void prefixSearch(String prefix, Node node, HashSet<String> result) {

        if(node != null) {
            if(node.isComplete) {
                result.add(prefix + node.storeChar);
            }

            prefixSearch(prefix, node.leftChild, result);
            prefixSearch(prefix + node.storeChar, node.centerChild, result);
            prefixSearch(prefix, node.rightChild, result);
        }

        if(search(prefix))
            result.add(prefix);

    }



}