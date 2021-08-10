package com.hyuuny.bookstore.service;

import com.hyuuny.bookstore.domain.item.Item;
import com.hyuuny.bookstore.repository.ItemRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ItemService {

  private final ItemRepository itemRepository;

  @Transactional
  public void saveItem(final Item item) {
    itemRepository.save(item);
  }

  public List<Item> findItems() {
    return itemRepository.findAll();
  }

  public Item findOne(final Long id) {
    return itemRepository.findOne(id);
  }

}
