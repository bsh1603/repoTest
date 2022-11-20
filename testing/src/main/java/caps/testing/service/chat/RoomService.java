package caps.testing.service.chat;

import caps.testing.domain.chat.Room;
import caps.testing.dto.chat.RoomDto;
import caps.testing.repository.MemberRepository;
import caps.testing.repository.chat.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomService {

    @Autowired
    private final RoomRepository roomRepository;

    @Autowired
    private final MemberRepository memberRepository;

    @Transactional
    public Long create_room(RoomDto roomDto, Long member_id){
        Long myTeamId = memberRepository.findMyTeamId(member_id);
        Room room = roomRepository.save(roomDto.toRoom());
        room.setTeamId(myTeamId);
        return room.getId();
    }
}














