package com.military.military.utils;

import android.app.Activity;
import android.content.Context;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by lichengcai on 2016/12/1.
 */

public class FileUtils {

    public static String hashKeyForDisk(String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    private static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    public static void writeFiles(Activity activity,String content,String fileName) {
        try {
            // 打开文件获取输出流，文件不存在则自动创建
            FileOutputStream fos = activity.openFileOutput(fileName,Context.MODE_PRIVATE);
            fos.write(content.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 读取文件内容
    public static String readFiles(Activity activity,String fileName) {
        String content = null;
        try {
            FileInputStream fis = activity.openFileInput(fileName);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            content = baos.toString();
            fis.close();
            baos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

    public static long delete(Activity activity,long time,String fileName) {
        if (time == 0) {
            time = System.currentTimeMillis();
        }
        long currentTime = System.currentTimeMillis();
        long l =  currentTime - time;
        if (l/1000/3600 > 3) {
            activity.deleteFile(fileName);
            time = currentTime;
            return time;
        }
        return time;
    }

}
