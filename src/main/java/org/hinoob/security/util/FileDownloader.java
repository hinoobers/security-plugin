package org.hinoob.security.util;

import java.io.File;

public class FileDownloader {

    public static void download(String url, File output) {
        try {
            java.io.InputStream in = new java.net.URL(url).openStream();
            java.io.FileOutputStream fos = new java.io.FileOutputStream(output);
            byte[] buf = new byte[4096];
            int n;
            while ((n = in.read(buf)) > 0) {
                fos.write(buf, 0, n);
            }
            fos.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
