package com.hyuuny.bookstore.repository;

import static org.springframework.util.ObjectUtils.isEmpty;

import com.hyuuny.bookstore.domain.item.Item;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ItemRepository {

  private final EntityManager em;

  public void save(final Item item) {
    if (isEmpty(item.getId())) {
      em.persist(item);
      return;
    }
    em.merge(item);
  }

  public Item findOne(final Long id) {
    return em.find(Item.class, id);
  }

  public List<Item> findAll() {
    return em.createQuery("select i from Item i", Item.class)
        .getResultList();
  }

}
