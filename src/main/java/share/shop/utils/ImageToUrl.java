package share.shop.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ImageToUrl {
    public static String toUrl(String pathUrl){
        if(pathUrl ==null) return null;

//        log.info("PathUrl " + pathUrl);

        String link= "http://localhost:2023/api/images/";
        String newPath  = pathUrl.replace("\\","/");
        String[] folder = newPath.split("/");
        if(folder.length>1)link = link + folder[folder.length-1];
        return  link;
    }
}
