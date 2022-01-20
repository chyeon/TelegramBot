package com.example.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Builder;
import lombok.Data;
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
		if(update.hasMessage() && update.getMessage().hasText()){
			log.info(update.getMessage().toString());

			String username = update.getMessage().getChat().getUserName();
			String chat_id = update.getMessage().getChatId().toString();
			String text = "Hi, you are @"+username;

			SendMessage sendMessage = SendMessage.builder().chatId(chat_id).text(text).build();
			try {
				execute(sendMessage);
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}

		}

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

	@Data
	public class myMessage {
		private String chat_id;
		private String text;

		myMessage(String chat_id, String text) {
			this.chat_id = chat_id;
			this.text = text;
		}
	}
	

	public void sendMessageWithURL(String content) {
		String url = "https://api.telegram.org/bot" + BOT_TOKEN + "/sendMessage";
		myMessage message = new myMessage(CHAT_ID, content);
		
		try {
			String param = new ObjectMapper().writeValueAsString(message);
			
			HttpHeaders headers = new HttpHeaders(); 
			headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
				
			HttpEntity<String> entity = new HttpEntity<>(param, headers);
			ResponseEntity<String> response = new RestTemplate().postForEntity(url, entity, String.class);
			System.out.println(response.getBody());

		}catch (Exception e) {
			log.error("Unhandled exception occurred while send Telegram.", e);
		}

	}

}
