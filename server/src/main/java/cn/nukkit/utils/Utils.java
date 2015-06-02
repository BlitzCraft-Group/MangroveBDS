package cn.nukkit.utils;

import cn.nukkit.Server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * author: MagicDroidX
 * Nukkit Project
 */
public class Utils {

    public static File WriteFile(String filename, InputStream inputStream) {
        if (inputStream == null) {
            Server.getInstance().getLogger().error("无法读取文件 " + filename);
            return null;
        }
        File file = new File(filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                Server.getInstance().getLogger().error("无法创建文件： " + filename);
                return null;
            }

        }
        byte buffer[] = new byte[4 * 1024];
        int length;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            while ((length = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, length);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            Server.getInstance().getLogger().error("对文件 " + filename + " 进行流操作时截取到异常");
            e.printStackTrace();
            return null;
        }
        return file;
    }

}
