package seung.shopping.backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import seung.shopping.backend.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
