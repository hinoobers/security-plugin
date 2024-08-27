package org.hinoob.security.util;

import java.io.File;

public class FileUtil {

    public static void delete(File file) {
        if(file.isDirectory()) {
            for(File f : file.listFiles()) {
                delete(f);
            }
        }

        file.delete();
    }
}
