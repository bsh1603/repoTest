package caps.testing.service.stock;

import caps.testing.domain.Team;
import caps.testing.domain.stock.Stock;
import caps.testing.dto.stock.StockRegisterDto;
import caps.testing.repository.MemberRepository;
import caps.testing.repository.TeamRepository;
import caps.testing.repository.stock.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@Slf4j
public class StockService {
    private final StockRepository stockRepository;
    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;

//    @Transactional
//    public Long registerStock(StockRegisterDto stockRegisterDto, Long id){
//
//        Stock stock = stockRegisterDto.toStock();
//        Stock result = stockRepository.save(stock);
//
//        Team myTeam = teamRepository.findMyTeam(id);
//        result.setTeam(myTeam);
//
//        return result.getId();
//    }
}
