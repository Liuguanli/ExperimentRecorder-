package com.leo.test;

import com.leo.ExpRecorder;
import com.leo.annotation.Recorder;
import com.leo.annotation.RecorderUtil;
import com.leo.annotation.Timer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class Foo {

    @Timer
    public void algo1() {
        ArrayList<Integer> l = new ArrayList<>();
        for (int i = 0; i < 10000000; i++) {
            l.add(1);
        }
    }

    @Timer
    @Recorder(recordType = ExpRecorder.ALL, fileName = "test.txt")
    public List<String> algo2() {
        List<String> result = new LinkedList(){{
            add("实验的参数");
            add("实验的目的");
            add("实验的结果");
        }};
        LinkedList<Integer> l = new LinkedList<>();
        for (int i = 0; i < 10000000; i++) {
            l.add(1);
        }
        return result;
    }

    public void algo3() {
        Vector<Integer> v = new Vector<>();
        for (int i = 0; i < 10000000; i++) {
            v.add(1);
        }
    }

    public static void main(String[] foo) {
        RecorderUtil tu = new RecorderUtil();
        tu.record("com.leo.test.Foo1");
    }
}
