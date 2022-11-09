package caps.testing.service;

import caps.testing.domain.Member;
import caps.testing.domain.Team;
import caps.testing.dto.TeamDto;
import caps.testing.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class TeamService {

    private final TeamRepository teamRepository;

    public Team register(Team team){
        Team saveTeam = teamRepository.save(team);
        return saveTeam;
    }
}
