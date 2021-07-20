package br.com.home.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.home.chat.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {

	Optional<ChatRoom> findBySenderIdAndRecipientId(String senderId, String recipientId);

}
