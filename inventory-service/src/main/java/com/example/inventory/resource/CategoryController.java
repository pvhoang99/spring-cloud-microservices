//package com.example.inventory.resource;
//
//import com.example.common.command.CommandBus;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/v1/categories")
//@RequiredArgsConstructor
//public class CategoryController {
//
//    private final CommandBus commandBus;
//
//    @PostMapping
//    public ResponseEntity<Long> create(@RequestBody CreateCategoryCommand command) {
//        return ResponseEntity.ok(this.commandBus.execute(command));
//    }
//
//}