package com.javarush.telegrambot;

import java.util.Map;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import static com.javarush.telegrambot.TelegramBotContent.*;


public class MyFirstTelegramBot extends MultiSessionTelegramBot {
    public static final String NAME = "jru_new_ex013_bot"; // TODO: добавьте имя бота в кавычках
    public static final String TOKEN = "8172624242:AAG9aYWlaEZ5SpoGrU_M_s9kNX1RiUaddcQ"; //TODO: добавьте токен бота в кавычках

    public MyFirstTelegramBot() {
        super(NAME, TOKEN);
    }

    @Override
    public void onUpdateEventReceived(Update updateEvent) {

        // отобразить сообщение в начале игры
        if (getMessageText().equals("/start")) {
            setUserGlory(0);
            sendPhotoMessageAsync("step_1_pic");
            sendTextMessageAsync(STEP_1_TEXT, Map.of("Взлом холодильника", "step_1_btn"));
        }
        if (getCallbackQueryButtonKey().equals("step_1_btn")) {
            addUserGlory(20);
            sendPhotoMessageAsync("step_2_pic");
            sendTextMessageAsync(STEP_2_TEXT,
                    Map.of("Взять сосиску! +20 славы", "step_2_btn",
                            "Взять рыбу! +20 славы", "step_2_btn",
                            "Скинуть банку с огурцами! +20 славы", "step_2_btn"));
        }
        // взламываем робот-пылесос
        if (getCallbackQueryButtonKey().equals("step_2_btn")) {
            addUserGlory(20);
            sendPhotoMessageAsync("step_3_pic");
            sendTextMessageAsync(STEP_3_TEXT, Map.of("Взлом робота-пылесоса", "step_3_btn"));
        }

        if (getCallbackQueryButtonKey().equals("step_3_btn")) {
            addUserGlory(30);
            sendPhotoMessageAsync("step_4_pic");
            sendTextMessageAsync(STEP_4_TEXT,
                    Map.of("Отправить робот-пылесос за едой! +30 славы", "step_4_btn",
                            "Покататься на роботе пылесосе! +30 славы", "step_4_btn",
                            "Убежать от робота пылесоса! +30 славы", "step_4_btn"));
        }
        // взламываем камеру GoPro
        if (getCallbackQueryButtonKey().equals("step_4_btn")) {
            addUserGlory(30);
            sendPhotoMessageAsync("step_5_pic");
            sendTextMessageAsync(STEP_5_TEXT, Map.of("Надеть и включить GoPro", "step_5_btn"));
        }

        if (getCallbackQueryButtonKey().equals("step_5_btn")) {
            addUserGlory(40);
            sendPhotoMessageAsync("step_6_pic");
            sendTextMessageAsync(STEP_6_TEXT,
                    Map.of("Снять на GoPro как ты бегаешь! +40 славы", "step_6_btn",
                            "Снять на GoPro как ты ешь! +40 славы", "step_6_btn",
                            "Снять на GoPro как ты играешься с клубком! +40 славы", "step_6_btn"));
        }
        // взламываем компьютер
        if (getCallbackQueryButtonKey().equals("step_6_btn")) {
            addUserGlory(40);
            sendPhotoMessageAsync("step_7_pic");
            sendTextMessageAsync(STEP_7_TEXT, Map.of("Взломать пароль от компьютера", "step_7_btn"));
        }

        if (getCallbackQueryButtonKey().equals("step_7_btn")) {
            addUserGlory(50);
            sendPhotoMessageAsync("step_8_pic");
            sendTextMessageAsync(STEP_8_TEXT,
                    Map.of("Залить на YouTube и хвастаться дворовым котам! +50 славы", "step_8_btn",
                            "Залить в Instagram и хвастаться дворовым котам! +50 славы", "step_8_btn",
                            "Залить в TikTok и хвастаться дворовым котам! +50 славы", "step_8_btn"));
        }
        // хвастаемся дворовым котам
        if (getCallbackQueryButtonKey().equals("step_8_btn")) {
//            sendPhotoMessageAsync("final_pic");
            sendImageMessageAsync("C:\\Users\\13\\Desktop\\TelegramBot\\src\\main\\resources\\images\\final.jpg");
            sendTextMessageAsync(FINAL_TEXT);
        }

        if (getMessageText().equals("/glory")) {
            sendTextMessageAsync("Ваша слава: " + getUserGlory());
        }


    }

//    public static void main(String[] args) throws TelegramApiException {
//        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
//        telegramBotsApi.registerBot(new MyFirstTelegramBot());
//    }

    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            MyFirstTelegramBot bot = new MyFirstTelegramBot();

            SetWebhook setWebhook = new SetWebhook();
            setWebhook.setUrl("https://your-app-name.up.railway.app/webhook"); // Вставь свой Railway URL

            botsApi.registerBot(bot, setWebhook);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}


//        if (getMessageText().equals("/start")) {
//sendTextMessageAsync("Hello");
//        }
//
//                if (getMessageText().equals("/bye")) {
//sendTextMessageAsync("Asta la vista, baby");
//        }
//
//
//                if (getMessageText().contains("img")) {
//sendPhotoMessageAsync("step_8_pic");
//        }
//
//                if (getMessageText().contains("/pet")) {
//sendTextMessageAsync("Ваше любимое животное?",
//                     Map.of("Кот", "cat", "Собака", "dog"));
//        }
//
//        if (getCallbackQueryButtonKey().equals("cat")) {
//sendPhotoMessageAsync("step_4_pic");
//        }
//
//                if (getCallbackQueryButtonKey().equals("dog")) {
//sendPhotoMessageAsync("step_6_pic");
//        }
//
//                if (getMessageText().equals("smile")) {
//var message = getLastSentMessage();
//editTextMessageAsync(message.getMessageId(), message.getText() + " :) ");
//        }
