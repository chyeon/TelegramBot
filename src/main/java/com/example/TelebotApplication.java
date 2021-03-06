package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import com.example.bot.TelegramMessageBot;

@SpringBootApplication
public class TelebotApplication {

	public static void main(String[] args) {

		try {
			TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
			botsApi.registerBot(new TelegramMessageBot());
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}

		SpringApplication.run(TelebotApplication.class, args);
	}

}
