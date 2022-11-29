package share.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import share.shop.services.TelegramService;


@RestController
@RequestMapping("/api/telegram")
public class TelegramController {

    @Autowired
    private TelegramService telegramService;

    String photoId ="AgACAgUAAxkBAAM0Y4X2n4Q8Pv4ySut0NTDQ_gf9y3EAAtq1MRvvVTBU6SK4jw1rTNYBAAMCAAN5AAMrBA";
    String videoId= "BAACAgUAAxkBAAM7Y4X9T2ds4IhwPy1j0-xBOaOcJCcAAp8IAALvVTBUZSam_TEvkVsrBA";
    String chatId ="5612057082";

    @GetMapping(value = "/images")
    public ResponseEntity getImage(){
       telegramService.sendPhotoByPhotoId(chatId,"test",photoId);
        return   ResponseEntity.ok().build();
    }

    @GetMapping(value = "/videos")
    public ResponseEntity getVideo(){
        telegramService.sendVideoByVideoId(chatId,"test",videoId);
        return   ResponseEntity.ok().build();
    }

    @GetMapping(value = "/group/sentMsg")
    public ResponseEntity sentMsgToGroup(){
        telegramService.sendMessageToTelegramGroup("chao cac ban");
        return   ResponseEntity.ok().build();
    }

    @GetMapping(value = "/group/sentImg")
    public ResponseEntity sentImageToGroup(){
      //  telegramService.sendPhotoToTelegramGroup("chao cac ban",photoId);
        return   ResponseEntity.ok().build();
    }



}
