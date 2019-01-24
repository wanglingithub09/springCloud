package com.test.designpattern.visitor;

import cn.hutool.core.lang.Console;

public class MyVisitor implements Visitor {
    @Override
    public void visit(Subject subject) {
        Console.log("访问主题："+subject.getSubject());
    }
}
