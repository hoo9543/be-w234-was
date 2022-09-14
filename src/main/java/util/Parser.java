package util;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Parsing {

    public static Map<String, String> getParamsFromString(String Str) {
        Map<String, String> params = new HashMap<String, String>();
        String[] keyValues = Str.split("&");
        for (String keyValue : keyValues) {
            String[] value = StringDivideAndCheckNum(keyValue,"=",2);
            params.put(value[0], value[1]);
        }
        return params;
    }

    public static String[] StringDivideAndCheckNum(String str, String delimiter, int CheckNum){
        String[] splitedStr = str.split(delimiter);
        if (splitedStr.length != CheckNum) {
            throw new IllegalArgumentException("");
        }
        return splitedStr;
    }


    public static String getBodyFromInputStream(BufferedReader br) throws IOException {
        return "";
    }

}