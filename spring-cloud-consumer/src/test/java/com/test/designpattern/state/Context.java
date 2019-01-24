package com.test.designpattern.state;

import lombok.Data;

@Data
public class Context {
    private State state;
    public Context(State state){
        this.state = state;
    }
    public void method(){
        if(state.getValue().equals("ON_LINE")){
            state.method1();
        }else if(state.getValue().equals("OFF_LINE")){
            state.method2();
        }
    }
}
