package caps.testing.repository;

import caps.testing.domain.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long> {

    Optional<Work> findById(Long id);

    @Query(value = "select w.work_start_time from work w where w.id = ?1", nativeQuery = true)
    Timestamp findStartTime(Long id);

    @Query(value = "select w.work_end_time from work w where w.id =?1", nativeQuery = true)
    Timestamp findEndTime(Long id);

    @Query(value = "select w.work_id from work w where w.work_end_time is null", nativeQuery = true)
    Long findNullId();

    @Modifying
    @Query(value = "UPDATE work w set w.work_end_time = :localDateTime where w.work_id = :id", nativeQuery = true)
    void updateEndTime(@Param("localDateTime") Timestamp localDateTime, @Param("id") Long id);

    @Modifying
    @Query(value = "UPDATE work w set w.work_time = timediff(w.work_end_time, w.work_start_time)", nativeQuery = true)
    void today_work_time();
}
