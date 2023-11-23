package seung.shopping.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seung.shopping.backend.entity.Item;
import seung.shopping.backend.entity.Member;

public interface MemberRepository extends JpaRepository<Item, Long> {
    Member findByEmailAndPassword(String email, String password);
}
