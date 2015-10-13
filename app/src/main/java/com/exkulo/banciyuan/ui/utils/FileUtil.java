package com.exkulo.banciyuan.ui.utils;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static java.io.File.*;

/**
 * Created by exkulo on 10/3/2015.
 */
public class FileUtil {

    private static String mRoot = Environment.getExternalStorageDirectory().toString();

    /**
     * 保存图片
     */
    public static void saveImage(byte[] buffer) throws IOException {
        Context context = ContextAccessor.getContextAccessor().getContext();
        File folder = new File(mRoot + separator + "ex_ciyuan");
        if (!folder.exists()) {
            if (!folder.mkdirs()) {
                throw new IOException("创建文件夹失败了诶");
            }
        }
        int count = folder.listFiles().length;
        File file = new File(folder, count + ".jpg");
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(buffer);
            fileOutputStream.flush();
        } catch (IOException e) {
            L.e(e);
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * 保存图片
     */
    public static void saveImage(byte[] buffer, String fileName) throws IOException {
        Context context = ContextAccessor.getContextAccessor().getContext();
        File folder = new File(mRoot + separator + "ex_ciyuan");
        if (!folder.exists()) {
            if (!folder.mkdirs()) {
                throw new IOException("创建文件夹失败了诶");
            }
        }
        int count = folder.listFiles().length;
        File file = new File(folder, count + " " + fileName + ".jpg");
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(buffer);
            fileOutputStream.flush();
        } catch (IOException e) {
            L.e(e);
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
