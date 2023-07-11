package io;

import java.io.File;
import java.io.FilenameFilter;

public class JPEGFileNameFilter implements FilenameFilter {
    @Override
    public boolean accept(File dir, String name) {
        if (name.endsWith(".jpeg")) return true;
        return false;
    }
}
