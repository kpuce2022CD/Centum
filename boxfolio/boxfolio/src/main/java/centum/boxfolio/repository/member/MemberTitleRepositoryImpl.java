package centum.boxfolio.repository.member;

import centum.boxfolio.entity.member.MemberTitle;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
@Transactional
@RequiredArgsConstructor
public class MemberTitleRepositoryImpl implements MemberTitleRepository{

    private final EntityManager em;

    @Override
    public List<MemberTitle> findMemberTitlesByMemberId(Long memberId) {
        return findAllMemberTitle().stream()
                .filter(mt -> mt.getMember().getId() == memberId)
                .collect(Collectors.toList());
    }

    @Override
    public List<MemberTitle> findAllMemberTitle() {
        return em.createQuery("select mt from MemberTitle mt", MemberTitle.class).getResultList();
    }
}
