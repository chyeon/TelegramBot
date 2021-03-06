package com.example.bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TelegramMessageBot extends TelegramLongPollingBot {
	private final String BOT_NAME = "BOT_NAME"; // Bot 이름
	private final String BOT_TOKEN = "BOT_TOKEN"; // Bot Token
	private final String CHAT_ID = "CHAT_ID"; // 수신자의 Chat ID

	@Override // 생성한 bot의 이름을 반환하는 메서드
	public String getBotUsername() {
		return BOT_NAME;
	}

	@Override // 발급 받은 token을 반환하는 메서드
	public String getBotToken() {
		return BOT_TOKEN;
	}

	@Override // Bot이 메세지를 받았을 때 처리하는 메서드
	public void onUpdateReceived(Update update) {
		// TODO
	}

	// Bot으로 메세지를 보내는 커스텀 메서드
	public void sendMessage(String sendMessage) {
		SendMessage message = new SendMessage(CHAT_ID, sendMessage); // CHAT_ID에 해당하는 방으로 메세지 전송

		try {
			execute(message);
		} catch (TelegramApiException e) {
			log.debug(e.getMessage());
		}
	}

}
