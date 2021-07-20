package br.com.home.chat;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ChatMessage {
	@Id
	private String id;
	private String chatId;
	private String senderId;
	private String recipientId;
	private String senderName;
	private String recipientName;
	private String content;
	private Date timestamp;
	private MessageStatus status;
}
