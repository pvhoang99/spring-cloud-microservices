package com.example.orderservice.controller;

import com.example.orderservice.client.AuthServiceFeignClient;
import com.example.orderservice.service.OrderStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lijingyao on 2017/11/8 16:04.
 */
@RestController
public class OrderController {

  @Autowired
  private OrderStateService orderStateService;

  @Autowired
  AuthServiceFeignClient authServiceFeignClient;

//  @Autowired
//  private OrderRepo repo;
//
//  /**
//   * 列出所有的订单列表
//   *
//   * @return
//   */
//  @RequestMapping(method = {RequestMethod.GET})
//  public ResponseEntity orders() {
//    String orders = orderStateService.listDbEntries();
//    return new ResponseEntity(orders, HttpStatus.OK);
//
//  }
//
//
//  /**
//   * 通过触发一个事件，改变一个订单的状态
//   *
//   * @param orderId
//   * @param event
//   * @return
//   */
//  @RequestMapping(value = "/{orderId}", method = {RequestMethod.POST})
//  public ResponseEntity processOrderState(@PathVariable("orderId") Integer orderId,
//      @RequestParam("event") OrderStatusChangeEvent event) {
//    Boolean result = orderStateService.change(orderId, event);
//    return new ResponseEntity(result, HttpStatus.OK);
//  }
//
//  @PostMapping
//  public ResponseEntity saveOrder(@RequestBody Order order) {
//    order.setStatus(OrderStatus.WAIT_PAYMENT);
//    return ResponseEntity.ok(repo.save(order));
//  }

  @GetMapping("/test")
  public Object test() {
    System.out.println("a");
    return authServiceFeignClient.testAPI();
  }

  @GetMapping("/security")
  public void security() {
    System.out.println("a");
    Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

  }

}
