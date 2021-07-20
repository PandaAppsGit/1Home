package br.com.home.chat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.home.repositorys.ChatMessageRepository;
import br.com.home.services.ChatRoomService;

@Service
public class ChatMessageService {
	@Autowired
	private ChatMessageRepository repository;
	@Autowired
	private ChatRoomService chatRoomService;

	public ChatMessage save(ChatMessage chatMessage) {
		chatMessage.setStatus(MessageStatus.RECEIVED);
		repository.save(chatMessage);
		return chatMessage;
	}

	public long countNewMessages(String senderId, String recipientId) {
		return repository.countBySenderIdAndRecipientIdAndStatus(senderId, recipientId, MessageStatus.RECEIVED);
	}

	public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
		Optional<String> chatId = chatRoomService.getChatId(senderId, recipientId, false);

		List<ChatMessage> messages = chatId.map(cId -> repository.findByChatId(cId)).orElse(new ArrayList<>());

		return messages;
	}

	public ChatMessage findById(String id) {

		Optional<ChatMessage> chatMessage = repository.findById(id);

		if (chatMessage.isPresent()) {
			ChatMessage db = chatMessage.get();

			db.setStatus(MessageStatus.DELIVERED);

			return repository.save(db);
		} else {
			return null;
		}

	}

}
