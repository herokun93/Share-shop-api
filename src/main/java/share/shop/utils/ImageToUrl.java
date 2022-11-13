package share.shop.utils;

public class ImageToUrl {
    public static String toUrl(String pathUrl){
        if(pathUrl ==null) return null;
        String link= "http://localhost:2023/api/images/getName/";
        String newPath  = pathUrl.replace("\\","/");
        String[] folder = newPath.split("/");
        link = link + folder[folder.length-1];
        return  link;
    }
}
