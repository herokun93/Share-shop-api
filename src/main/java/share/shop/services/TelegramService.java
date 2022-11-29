package share.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import share.shop.telegram.TelegramClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class TelegramService {

    @Autowired
    TelegramClient telegramClient;

    public void sendPhotoToTelegramGroup(String caption, String photo)  {
        List chatIdList = new ArrayList();
        chatIdList.add("@Rfd2Qpu3D9MwZDI1");

        for(Object chatId: chatIdList){
            try {
                telegramClient.sendPhotoByPhotoId(chatId,caption, photo);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void sendPhotoFileToTelegramGroup(String caption, MultipartFile photo) throws Exception {
        List chatIdList = new ArrayList();
        chatIdList.add("@MinoTest1");
        chatIdList.add("@minoTestChannel");
        for(Object chatId: chatIdList){
            telegramClient.sendPhotoFile(chatId,caption, photo);
        }
    }

    public void sendMessageToTelegramGroup(String message)  {
        List chatIdList = new ArrayList();
        chatIdList.add("@myshopV1");
        for(Object chatId: chatIdList){
            try {
                telegramClient.sendMessage(message, chatId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void sendPhotoByPhotoId(Object chatID, String caption, String photo){
        try {
            telegramClient.sendPhotoByPhotoId(chatID,caption,photo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void sendVideoByVideoId(Object chatID, String caption, String video){
        try {
            telegramClient.sendVideoByVideoId(chatID,caption,video);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}