package com.oneeyedmen.okeydoke.testutils;

import org.junit.rules.ExternalResource;

import java.io.File;
import java.io.FilenameFilter;

public class CleanDirectoryRule extends ExternalResource {

    public static final boolean AFTER_TOO = true;

    private final File dir;
    private final boolean cleanAfterToo;

    public CleanDirectoryRule(File dir) {
        this(dir, false);
    }

    public CleanDirectoryRule(File dir, boolean cleanAfterToo) {
        this.dir = dir;
        this.cleanAfterToo = cleanAfterToo;
    }

    @Override
    protected void before() throws Throwable {
        dir.mkdirs();
        clean();
    }

    @Override
    protected void after() {
        if (cleanAfterToo)
            clean();
    }

    private void clean() {
        File[] files = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".approved") || name.endsWith("actual");
            }
        });
        for (File file : files) {
            file.delete();
        }
    }

    public static File dirForPackage(String srcRoot, Object o) {
        return new File(new File(srcRoot), packageFor(o).getName().replaceAll("\\.", "/"));
    }

    private static Package packageFor(Object o) {
        return (o instanceof Class) ? ((Class) o).getPackage() : o.getClass().getPackage();
    }
}
