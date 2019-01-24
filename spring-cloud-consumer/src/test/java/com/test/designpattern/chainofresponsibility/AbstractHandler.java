package com.test.designpattern.chainofresponsibility;

import lombok.Data;

@Data
public abstract class AbstractHandler {
    private Handler handler;
}
