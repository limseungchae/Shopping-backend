package seung.shopping.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import seung.shopping.backend.entity.Item;
import seung.shopping.backend.repository.ItemRepository;
import seung.shopping.backend.service.ItemService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ItemController {

    ItemRepository itemRepository;

    @GetMapping("/api/items")
    public List<Item> getItems() {
        List<Item> items = itemRepository.findAll();

        return items;
    }


}
