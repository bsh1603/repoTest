package caps.testing.repository;

import caps.testing.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class ManageRepository {

    private final EntityManager em;

    public Member findByCode(String authentication_code){
        Member findmember = em.find(Member.class, authentication_code);
        return findmember;
    }
}
