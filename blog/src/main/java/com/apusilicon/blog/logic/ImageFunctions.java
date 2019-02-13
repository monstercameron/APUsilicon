package com.apusilicon.blog.logic;

import javax.xml.bind.DatatypeConverter;
import java.io.*;

/**
 *
 * @author monstercameron
 * 
 *                 This class handles ima
 */
public class ImageFunctions {

    public static void b64ToImage(String base64String, File file){
        String[] strings = base64String.split(",");

        // switch(strings[0])
        // {//check image's extension
        //     case "data:image/jpeg;base64":
        //         extension = "jpeg";
        //         break;
        //     case "data:image/png;base64":
        //         extension = "png";
        //         break;
        //     default://should write cases for more images types
        //         extension = "jpg";
        //         break;
        // }

        // convert base64 string to binary data
        byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);

        // // String path = "C:\\Users\\Ene\\Desktop\\test_image." + extension;
        // String path = "C:\\Users\\Ene\\Desktop\\test_image." + extension;
        // File file = new File(path);
        
        try(OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))){
            outputStream.write(data);
        }catch(
        IOException e){
            e.printStackTrace();
        }
    }

}
