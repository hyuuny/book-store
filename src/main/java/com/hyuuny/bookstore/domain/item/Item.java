package com.hyuuny.bookstore.domain.item;

import com.hyuuny.bookstore.domain.Category;
import com.hyuuny.bookstore.exception.NotEnoughStockException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@DiscriminatorColumn(name = "dtype")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Setter
@Getter
@Entity
abstract public class Item {

  @Id
  @GeneratedValue
  @Column(name = "item_id")
  private Long id;

  private String name;
  private int price;
  private int stockQuantity;

  @ManyToMany(mappedBy = "items")
  private List<Category> categories = new ArrayList<>();

  public void addStock(int quantity) {
    this.stockQuantity += quantity;
  }

  public void removeStock(int quantity) {
    int resultStock = this.stockQuantity -= quantity;
    if (resultStock < 0) {
      throw new NotEnoughStockException("need more stock");
    }
    this.stockQuantity = resultStock;
  }

}
