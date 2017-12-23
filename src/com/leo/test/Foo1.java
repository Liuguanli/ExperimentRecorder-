package com.leo.test;

import com.leo.ExpRecorder;
import com.leo.annotation.Recorder;
import com.leo.annotation.Timer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class Foo1 {

    @Timer
    public void algo1() {
        ArrayList<Integer> l = new ArrayList<>();
        for (int i = 0; i < 10000000; i++) {
            l.add(1);
        }
    }

    @Timer
    @Recorder(recordType = ExpRecorder.ALL, fileName = "foo2_testalgo2.txt")
    public List<String> testalgo2() {
        List<String> result = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            result.addAll(algo2());
        }
        return result;
    }

    @Timer
    @Recorder(recordType = ExpRecorder.ALL, fileName = "foo2.txt")
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

}
