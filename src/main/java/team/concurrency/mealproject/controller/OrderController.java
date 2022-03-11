package team.concurrency.mealproject.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.concurrency.mealproject.dto.TaomlarSoni;
import team.concurrency.mealproject.dto.order.OrderCreateDto;
import team.concurrency.mealproject.dto.order.OrderUpdateDto;
import team.concurrency.mealproject.service.order.OrderServiceImp;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderServiceImp service;

    @PostMapping("api/order")
    public ResponseEntity<String> create(@RequestBody OrderCreateDto dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("api/order/{id}")
    public ResponseEntity<Boolean> update(@RequestBody OrderUpdateDto dto, @PathVariable String id) {
        dto.setId(id);
        return new ResponseEntity<>(service.update(dto), HttpStatus.OK);
    }

    @DeleteMapping("api/order/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "api/order",method = RequestMethod.GET)
    public ResponseEntity<List<TaomlarSoni>> ordersNumber(){
        return new ResponseEntity<>(service.ordersNumber(),HttpStatus.OK);
    }

}
