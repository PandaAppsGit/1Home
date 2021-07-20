package br.com.home.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.home.chat.ChatRoom;
import br.com.home.repositorys.ChatRoomRepository;

@Service
public class ChatRoomService {

	@Autowired
	private ChatRoomRepository chatRoomRepository;

	public Optional<String> getChatId(String senderId, String recipientId, boolean createIfNotExist) {

		Optional<ChatRoom> chatRoom = chatRoomRepository.findBySenderIdAndRecipientId(senderId, recipientId);

		if (chatRoom.isPresent()) {

			if (!createIfNotExist) {
				return Optional.empty();
			}
			String chatId = String.format("%s_%s", senderId, recipientId);

			ChatRoom senderRecipient = ChatRoom.builder().chatId(chatId).senderId(senderId).recipientId(recipientId)
					.build();

			ChatRoom recipientSender = ChatRoom.builder().chatId(chatId).senderId(recipientId).recipientId(senderId)
					.build();
			chatRoomRepository.save(senderRecipient);
			chatRoomRepository.save(recipientSender);

			return Optional.of(chatId);

		} else {
			return Optional.empty();
		}

	}

}
