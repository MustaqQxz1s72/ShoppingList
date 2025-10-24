package com.example.shoppinglist.controller;

import com.example.shoppinglist.model.Item;
import com.example.shoppinglist.repository.ItemRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity; // ✅ add this line
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @PostMapping
    public Item addItem(@RequestBody Item item) {
        return itemRepository.save(item);
    }

    @GetMapping
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @GetMapping("/autocomplete")
    public List<Item> autocomplete(@RequestParam String prefix) {
        return itemRepository.findByNameStartingWithIgnoreCase(prefix);
    }

    @PutMapping("/{id}/bought")
    public ResponseEntity<Item> markAsBought(@PathVariable Long id) {
        Item item = itemRepository.findById(id).orElseThrow();
        item.setBought(true);
        itemRepository.save(item);
        return ResponseEntity.ok(item);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemRepository.deleteById(id);
    }

    @GetMapping("/recommend")
    public List<String> recommend(@RequestParam List<String> items) {
        List<String> recommendations = new ArrayList<>();
        if (items.contains("gin") && !items.contains("tonic")) recommendations.add("tonic");
        if (items.contains("chicken") && !items.contains("rice")) recommendations.add("rice");
        if (items.contains("tomato") && !items.contains("basil")) recommendations.add("basil");
        return recommendations;
    }
}
