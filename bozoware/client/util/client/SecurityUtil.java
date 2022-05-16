package bozoware.client.util.client;

import bozoware.launcher.LaunchClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtil {

    public static String getHWID(String username) {
        try{
            String toEncrypt =

                    System.getProperty("user.name") +
                    username.toLowerCase() +
                    System.getenv("PROCESSOR_IDENTIFIER") +
                    System.getenv("COMPUTERNAME");

            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(toEncrypt.getBytes());
            StringBuilder hexString = new StringBuilder();
            byte[] byteData = md.digest();
            for (byte aByteData : byteData) {
                String hex = Integer.toHexString(0xff & aByteData);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString().toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }
    public static String getChecksum() {
        File currentJavaJarFile = new File(LaunchClient.class.getProtectionDomain().getCodeSource().getLocation().getPath(),"BozowareReborn.jar");
        String filepath = currentJavaJarFile.getAbsolutePath();
        StringBuilder sb = new StringBuilder();
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            FileInputStream fis = new FileInputStream(filepath);
            byte[] dataBytes = new byte[1024];
            int nread = 0;

            while((nread = fis.read(dataBytes)) != -1)
                md.update(dataBytes, 0, nread);

            byte[] mdbytes = md.digest();

            for(int i=0; i<mdbytes.length; i++)
                sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100 , 16).substring(1));
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
