package ru.job4j.io;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
        System.out.println();

        String name = "Andrey Morozov";
        int age = 26;
        LOG.debug("User info name : {}, age : {}", name, age);
        System.out.println();

        int i = 5;
        short s = 3;
        byte b = 1;
        long l = 4L;
        float f = 2F;
        double d = 3D;
        char ch = 'x';
        boolean bool = true;
        LOG.debug("int - {};  short - {}; byte - {}; long - {}; float - {}; double - {}; char - {}; boolean - {};", i, s, b, l, f, d, ch, bool);
    }
}

