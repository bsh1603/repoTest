package caps.testing.repository;

import caps.testing.domain.Member;
import caps.testing.dto.MemberDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{

    Optional<Member> findByEmail(String email);

    @Query(value = "select * from member m where m.authentication_code = ?1", nativeQuery = true)
    List<Member> findAllByCodeLike(String authentication_code);

    @Query(value = "select m.authentication_code from member m")
    HashSet<String> findAllCode();

    @Query(value = "select * from member m where m.team_id = ?1", nativeQuery = true)
    List<Member> findAllMyTeam(Long team_id);

}
