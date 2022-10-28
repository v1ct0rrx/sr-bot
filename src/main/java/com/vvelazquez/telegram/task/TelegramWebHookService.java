package com.vvelazquez.telegram.task;

import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramWebHookService extends TelegramWebhookBot {
	
	@Value("${telegram.token}")
	private String telegramToken;
	
	@Value("${telegram.webhook.user}")
	private String telegramWebHookUser;

	@Override
	public BotApiMethod onWebhookUpdateReceived(Update update) {
		if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(update.getMessage().getChatId().toString());
            sendMessage.setText("Well, all information looks like noise until you break the code.");
            return sendMessage;
        }
		
		return null;
	}

	@Override
	public String getBotUsername() {
		return telegramWebHookUser;
	}

	@Override
	public String getBotPath() {
		return telegramWebHookUser;
	}

	@Override
	public String getBotToken() {
		// TODO Auto-generated method stub
		return telegramToken;
	}

}
