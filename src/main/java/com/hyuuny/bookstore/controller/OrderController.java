package com.hyuuny.bookstore.controller;

import com.hyuuny.bookstore.repository.OrderSearch;
import com.hyuuny.bookstore.service.ItemService;
import com.hyuuny.bookstore.service.MemberService;
import com.hyuuny.bookstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class OrderController {

  private final OrderService orderService;
  private final MemberService memberService;
  private final ItemService itemService;

  @GetMapping("/order")
  public String createForm(Model model) {
    model.addAttribute("members", memberService.findMembers());
    model.addAttribute("items", itemService.findItems());

    return "order/orderForm";
  }

  @PostMapping("/order")
  public String order(
      @RequestParam final Long memberId,
      @RequestParam final Long itemId,
      @RequestParam final int count
  ) {
    orderService.order(memberId, itemId, count);
    return "redirect:/orders";
  }

  @GetMapping("/orders")
  public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {
    model.addAttribute("orders", orderService.findOrders(orderSearch));
    return "order/orderList";
  }

  @PostMapping("/orders/{orderId}/cancel")
  public String cancelOrder(@PathVariable("orderId") final Long orderId) {
    orderService.cancelOrder(orderId);
    return "redirect:/orders";
  }

}
