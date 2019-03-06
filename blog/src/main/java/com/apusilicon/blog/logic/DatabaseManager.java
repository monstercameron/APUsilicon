package com.apusilicon.blog.logic;

import com.apusilicon.blog.classes.real.APU;
import com.apusilicon.blog.classes.real.Accessory;
import com.apusilicon.blog.classes.real.Battery;
import com.apusilicon.blog.classes.real.Display;
import com.apusilicon.blog.classes.real.Laptop;
import com.apusilicon.blog.classes.real.Ram;
import com.apusilicon.blog.classes.real.Storage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.springframework.stereotype.Component;

/**
 *
 * @author monstercameron
 */
@Component
public class DatabaseManager {

    public ArrayList<String> kvPairs(String dict) {
        //removes the {}
        dict = dict.substring(1, dict.length() - 1);
        //splits the dictionary in lines
        ArrayList<String> pairs = new ArrayList<>(Arrays.asList(dict.split(",")));
        System.out.println(pairs);
        return pairs;
    }

    public HashMap<String, String> kvPairsToMap(ArrayList<String> kvPairs) {
        HashMap<String, String> mapping = new HashMap<>();
        for (String kvPair : kvPairs) {
            kvPair = kvPair.substring(1, kvPair.length() - 1);
            String[] keysVals = kvPair.split("\":\"");

            System.out.println(kvPair);
            try {
                mapping.put(keysVals[0], keysVals[1]);
            } catch (ArrayIndexOutOfBoundsException ex) {
                ex.printStackTrace();
            }

        }
        System.out.println(mapping);
        return mapping;
    }

    public HashMap<String, Object> buildLaptop(String buildDict) {
        HashMap<String, Object> mapping = new HashMap<>();
        mapping.put("laptop", new Laptop());
        mapping.put("apu", new APU());
        mapping.put("accessory", new Accessory());
        mapping.put("battery", new Battery());
        mapping.put("display", new Display());
        mapping.put("ram", new Ram());
        mapping.put("storage", new Storage());
        
        HashMap<String, String> kvMap = kvPairsToMap(kvPairs(buildDict));
        
                

        return mapping;
    }

}
