package caps.testing.service.chat;

import caps.testing.domain.chat.Message;
import caps.testing.dto.chat.MessageDto;
import caps.testing.repository.chat.MessageRepository;
import caps.testing.repository.chat.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageService {

    @Autowired
    private final MessageRepository messageRepository;

    @Transactional
    public Long create_message(MessageDto messageDto){
        Message message = messageRepository.save(messageDto.toMessage());
        return message.getId();
    }
}













