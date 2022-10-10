package jcano.awspractice.repository;

import jcano.awspractice.entity.PopularEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.UUID;

@Repository
public interface PopularRepository extends JpaRepository<PopularEntity, BigInteger> {
    PopularEntity findByProductId(UUID productId);

    @Transactional
    void deleteByProductId(UUID productId);
}
