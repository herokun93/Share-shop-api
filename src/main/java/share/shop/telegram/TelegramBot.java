package share.shop.telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;


import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import share.shop.models.Shop;
import share.shop.repositories.ShopRepository;
import share.shop.repositories.UserRepository;


import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.springframework.util.ResourceUtils.getFile;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShopRepository shopRepository;

    final BotConfig config;

    static final String HELP_TEXT = "This bot is created to demonstrate Spring capabilities.\n\n" +
            "You can execute commands from the main menu on the left or by typing a command:\n\n" +
            "Type /start to see a welcome message\n\n" +
            "Type /mydata to see data stored about yourself\n\n" +
            "Type /help to see this message again";

    static final String YES_BUTTON = "YES_BUTTON";
    static final String NO_BUTTON = "NO_BUTTON";

    static final String ERROR_TEXT = "Error occurred: ";

    public TelegramBot(BotConfig config) {
        this.config = config;
        List<BotCommand> listofCommands = new ArrayList<>();
        listofCommands.add(new BotCommand("/start", "get a welcome message"));
        listofCommands.add(new BotCommand("/mydata", "get your data stored"));
        listofCommands.add(new BotCommand("/deletedata", "delete my data"));
        listofCommands.add(new BotCommand("/help", "info how to use this bot"));
        listofCommands.add(new BotCommand("/settings", "set your preferences"));
        try {
            this.execute(new SetMyCommands(listofCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            log.error("Error setting bot's command list: " + e.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {

        log.info("data");
        long chatId = update.getMessage().getChatId();

        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();



            if(messageText.contains("/shop")){
                Optional<Shop> shop = shopRepository.findByTelegramId(Long.toString(chatId));
                if(shop.isEmpty())prepareAndSendMessage(chatId,"not is shop");
                else{
                    prepareAndSendMessage(chatId,shop.get().getAddress());
                }

           
            }
        }
        else if (update.hasMessage() && update.getMessage().hasPhoto()) {

            Message message = update.getMessage();

            // get the last photo - it seems to be the bigger one
            List<PhotoSize> photos = message.getPhoto();
            PhotoSize photo = photos.get(photos.size() - 1);
            String id = photo.getFileId();
            try {
                GetFile getFile = new GetFile();
                getFile.setFileId(id);
                String filePath = execute(getFile).getFilePath();
                log.info("id filed {}",id);
                log.info("FilePath {}",filePath);

                File file = downloadFile(filePath);
                log.info("File out path {}",file.getPath());

                SendPhoto sendPhoto = new SendPhoto();

              //  sendPhotoMessage(chatId, id, caption);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        else if (update.hasMessage() && update.getMessage().hasVideo()) {
            String fileId = update.getMessage().getVideo().getFileId();
            GetFile getVide = new GetFile(fileId);
            log.info("file video id {}",getVide);
        }
    }

    private void prepareAndSendMessage(long chatId, String textToSend){
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);
        executeMessage(message);
    }
    private void executeMessage(SendMessage message){
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error(ERROR_TEXT + e.getMessage());
        }
    }
}
