package com.apusilicon.blog.logic;

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
    
    public HashMap<String,String> kvPairsToMap(ArrayList<String> kvPairs){
        HashMap<String,String> mapping = new HashMap<>();
        for(String kvPair: kvPairs){
            kvPair = kvPair.substring(1, kvPair.length() - 1);
            String[] keysVals= kvPair.split("\":\"");
            
            System.out.println(kvPair);
            try{
                mapping.put(keysVals[0], keysVals[1]);
            }catch(ArrayIndexOutOfBoundsException ex){
                ex.printStackTrace();
            }
            
        }
        System.out.println(mapping);
        return mapping;
    }

}
