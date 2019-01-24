package com.test.designpattern.Facade;

public class Computer {

    private CPU cpu;
    private Disk disk;
    private Memory memory;
    public Computer(){
        this.cpu = new CPU();
        this.disk = new Disk();
        this.memory = new Memory();
    }

    public void startup(){
        cpu.startup();
        disk.startup();
        memory.startup();
    }

    public void shutdown(){
        cpu.shutdown();
        disk.shutdown();
        memory.shutdown();
    }
}
