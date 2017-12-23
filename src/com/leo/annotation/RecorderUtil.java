package com.leo.annotation;

import com.leo.ExpRecorder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by apple on 2017/12/23.
 */
public class RecorderUtil {
    public void record(String... classNames) {
        // 获取当前类型名字
        if (classNames.length == 0) {
            classNames = new String[1];
            classNames[0] = Thread.currentThread().getStackTrace()[2].getClassName();
        }
        for (int i = 0; i < classNames.length; i++) {
            String className = classNames[i];
            System.out.println("current className(expected): " + className);
            try {
                Class c = Class.forName(className);
                Object obj = c.newInstance();
                Method[] methods = c.getDeclaredMethods();
                for (Method m : methods) {
                    // 判断该方法是否包含Timer注解
                    if (m.isAnnotationPresent(Timer.class)) {
                        System.out.println(m.getName());
                        m.setAccessible(true);
                        long start = 0, end = 0;
                        if (m.isAnnotationPresent(Recorder.class)) {
                            start = System.currentTimeMillis();
                            List<String> result = (List) m.invoke(obj);
                            // 执行该方法
                            end = System.currentTimeMillis();
                            if (result == null) {
                                throw new NullPointerException("you should return a List");
                            }
                            Recorder annotation = m.getAnnotation(Recorder.class);
                            result.add(m.getName() + "耗时->" + String.valueOf(end - start) + "ms");
                            ExpRecorder recorder = new ExpRecorder(result, annotation.recordType(), annotation.fileName());
                            recorder.print();
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
