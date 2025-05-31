package com.example.troubleshooting.log_troubleshooter.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/log/chat")
@Configuration
public class ChatController {

	private final ChatClient chatClient;

	public ChatController(OpenAiChatModel chatModel) {
		this.chatClient = ChatClient.create(chatModel);

	}

	@GetMapping
	public Mono<String> askQuestion(@RequestParam String question) {

		Prompt prompt = new Prompt(new UserMessage(question));

		return Mono.fromSupplier(() -> chatClient.prompt(prompt).call().content());
	}

}
