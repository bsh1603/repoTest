package caps.testing.service;

import caps.testing.domain.Work;
import caps.testing.dto.work.WorkEndDto;
import caps.testing.dto.work.WorkStartDto;
import caps.testing.repository.WorkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class WorkService {

    @Autowired
    private final WorkRepository workRepository;

    @Transactional
    public Long save_startTime(WorkStartDto workStartDto){
        Work startWork = workRepository.save(workStartDto.toStartWork());
        return startWork.getId();
    }

    public Long find_null_id(){
        Long nullId = workRepository.findNullId();
        return nullId;
    }

    @Transactional
    public void save_endTime(WorkEndDto workEndDto){
        Long nullId = workRepository.findNullId();
        workRepository.updateEndTime(workEndDto.getWork_end_time(), nullId);
    }

    @Transactional
    public void calculate_work_time(){
        workRepository.today_work_time();
    }

}
