package io;

import java.io.File;
import java.io.FileFilter;

public class JPGFileFilter implements FileFilter {
    @Override
    public boolean accept(File file) {
        if (file.isFile()) {
            if (file.getName().endsWith(".jpeg")) return true;
        }
        return false;
    }
}
