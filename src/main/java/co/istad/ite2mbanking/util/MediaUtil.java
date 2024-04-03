package co.istad.ite2mbanking.util;

public class MediaUtil {
    public static String extractExtension(String mediaName){
        int lastDotIndex = mediaName
                .lastIndexOf(".");
        String extension = mediaName
                .substring(lastDotIndex+1);

        return  extension;
    }

}
