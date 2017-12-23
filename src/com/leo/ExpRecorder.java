package com.leo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by apple on 2017/12/23.
 */
public class ExpRecorder {

    public static final int CONSOLE_PRINT = 0;
    public static final int FILE_PRINT = 1;
    public static final int ALL = 2;

    private int printType;

    //    public Map<String, String> expInfoMap;
    public List<String> expInfoList;
    private String fileName = "default_" + System.currentTimeMillis();

    public ExpRecorder() {
//        expInfoMap = new LinkedHashMap<>();
        expInfoList = new LinkedList<>();
    }

    public ExpRecorder(List list) {
        expInfoList = list;
    }

    public ExpRecorder(List list, int printType) {
        expInfoList = list;
        this.printType = printType;
    }

    public ExpRecorder(List list, int printType, String fileName) {
        expInfoList = list;
        this.printType = printType;
        this.fileName = fileName;
    }

    public void recordConfig(String key, String value) {

    }

    public void record(String info) {
        expInfoList.add(info);
    }

    public void print() {
        print(this.printType);
    }

    public void print(int printType) {
        PrintWriter consolePrinter = null;
        PrintWriter filePrinter = null;
        switch (printType) {
            case CONSOLE_PRINT:
                consolePrinter = new PrintWriter(System.out);
                break;
            case FILE_PRINT:
                try {
                    filePrinter = new PrintWriter(new File(fileName));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case ALL:
                consolePrinter = new PrintWriter(System.out);
                try {
                    filePrinter = new PrintWriter(new File(fileName));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
        }
        for (String line : expInfoList) {
            if (consolePrinter != null) {
                consolePrinter.write(line + "\r\n");
            }
            if (filePrinter != null)
                filePrinter.write(line + "\r\n");
        }
        if (filePrinter != null) {
            filePrinter.close();
        }
        if (consolePrinter != null) {
            consolePrinter.close();
        }
    }
}
