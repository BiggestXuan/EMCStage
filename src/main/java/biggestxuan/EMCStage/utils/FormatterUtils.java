package biggestxuan.EMCStage.utils;

import java.util.ArrayList;

public class FormatterUtils {
    public static String thousandSign(long text){
        return thousandSign(String.valueOf(text));
    }

    public static String thousandSign(String text){
        int len = text.length();
        ArrayList<String> stringContainer = new ArrayList<>();
        while(len>3){
            stringContainer.add(text.substring(len-3,len));
            len-=3;
        }
        stringContainer.add(text.substring(0,len));
        StringBuilder buffer = new StringBuilder();
        for(int i = stringContainer.size()-1;i>=0;i--){
            buffer.append(stringContainer.get(i)).append(",");
        }
        buffer.deleteCharAt(buffer.length()-1);
        return buffer.toString();
    }

    public static String format(long value){
        return format(String.valueOf(value));
    }

    public static String format(String text){
        if(text.length() <= 6){
            return thousandSign(text);
        }
        return KMT(text);
    }

    public static String KMT(String text){
        if(text.length() <= 6){
            return format(text);
        }
        String flag = getFlag(text);
        return formatAfter(text)+flag;
    }

    private static String formatAfter(String value){
        int c = value.length() - 1;
        if(c < 3) return value;
        if(c % 3 == 0){
            return value.charAt(0)+"."+value.substring(1,3);
        }
        if(c % 3 == 1){
            return value.substring(0,2)+"."+value.substring(2,4);
        }
        return value.substring(0,3)+"."+value.substring(3,5);
    }

    private static String getFlag(String value){
        int c = value.length();
        String o;
        if(c <= 6 && c >= 4){
            o = "K";
        }else if(c <= 9 && c >= 7){
            o = "M";
        }else if(c <= 12 && c >= 10){
            o = "G";
        }else if(c <= 15 && c >= 13){
            o = "T";
        }else if(c <= 18 && c >= 16){
            o = "P";
        }else o = "E";
        return o;
    }
}
