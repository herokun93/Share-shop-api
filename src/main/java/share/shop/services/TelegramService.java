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

    public void sendPhotoToTelegramGroup(String caption, String photo) throws Exception {
        List chatIdList = new ArrayList();
        chatIdList.add("@MinoTest1");
        chatIdList.add("@minoTestChannel");

        for(Object chatId: chatIdList){
            telegramClient.sendPhoto(chatId,caption, photo);
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

    public void sendMessageToTelegramGroup(String message) throws Exception {
        List chatIdList = new ArrayList();
        chatIdList.add("@MinoTest1");
        chatIdList.add("@minoTestChannel");
        for(Object chatId: chatIdList){
            telegramClient.sendMessage(message, chatId);
        }
    }
}