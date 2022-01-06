package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.bot.TelegramMessageBot;

@SpringBootTest
@AutoConfigureMockMvc
public class TelegramMessageTest {
	@Autowired
	private TelegramMessageBot bot;
	
	@Test
	public void sendMessageTest() {
		bot.sendMessage("Hi, sendMessageTest");
	}
	@Test
	public void sendMessageWithURLTest() {
		bot.sendMessageWithURL("Hi, sendMessageWithURLTest");
	}
}
