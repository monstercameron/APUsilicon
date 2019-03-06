package com.apusilicon.blog.logic;

import javax.xml.bind.DatatypeConverter;
import java.io.*;

/**
 *
 * @author monstercameron
 *
 * This class handles images
 */
public class ImageHandler {

    public static void b64ToImage(String base64String, File file) {
        
        String[] strings = base64String.split(",");
//        String extension;
//        switch (strings[0]) {//check image's extension
//            case "data:image/jpeg;base64":
//                extension = "jpeg";
//                break;
//            case "data:image/png;base64":
//                extension = "png";
//                break;
//            default://should write cases for more images types
//                extension = "jpg";
//                break;
//        }
        
        // convert base64 string to binary data
        byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
        String path = file.toString();// + "." + extension;
        file = new File(path);
        System.out.println(file.toString());
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            System.out.println(file.getCanonicalPath());
            outputStream.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String getExtension(String base64String){
        String[] strings = base64String.split(",");
        String extension;
        switch (strings[0]) {//check image's extension
            case "data:image/jpeg;base64":
                extension = "jpeg";
                break;
            case "data:image/png;base64":
                extension = "png";
                break;
            default://should write cases for more images types
                extension = "jpg";
                break;
        }
        return extension;
    }

}
