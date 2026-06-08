package it.unicam.Hackathon.repository;

import it.unicam.Hackathon.entity.CallSupporto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RepositoryCallSupporto extends JpaRepository<CallSupporto, Long> {
    List<CallSupporto> findByMentoreId(Long mentoreId);
    List<CallSupporto> findBySupportoId(Long supportoId);
}