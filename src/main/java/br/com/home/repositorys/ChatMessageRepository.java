package br.com.home.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.home.chat.ChatMessage;
import br.com.home.chat.MessageStatus;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, String> {

	long countBySenderIdAndRecipientIdAndStatus(String senderId, String recipientId, MessageStatus status);

	List<ChatMessage> findByChatId(String chatId);

}
