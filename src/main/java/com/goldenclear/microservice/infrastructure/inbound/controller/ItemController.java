package com.goldenclear.microservice.infrastructure.inbound.controller;


import com.goldenclear.microservice.application.ItemService;
import com.goldenclear.microservice.domain.model.Item;
import com.goldenclear.microservice.infrastructure.inbound.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping("/by-average/{rating}")
    public ResponseEntity<Response<List<Item>>> findItems(@PathVariable Double rating) {

        List<Item> result = itemService.findItemsWithAverageRatingLowerThan(rating);

        if (result.isEmpty()) {
            return ResponseEntity.ok(
                    Response.empty("No se encontraron item para el " + rating)
            );
        }

        return ResponseEntity.ok(
                Response.success(result, "Listado de items")
        );
    }

    @GetMapping("/by-rating/{rating}")
    public ResponseEntity<Response<List<String>>> findItems(@PathVariable Long rating) {

        List<String> result = itemService.getTitles(rating);

        if (result.isEmpty()) {
            return ResponseEntity.ok(
                    Response.empty("No se encontraron item para la " + rating)
            );
        }

        return ResponseEntity.ok(
                Response.success(result, "Listado de items")
        );
    }

    @GetMapping("/getTitles/{rating}")
    public ResponseEntity<Response<List<String>>> getTitles(@PathVariable Double rating) {

        List<String> result = itemService.getTitles(rating);

        if (result.isEmpty()) {
            return ResponseEntity.ok(
                    Response.empty("No se encontraron item para la " + rating)
            );
        }

        return ResponseEntity.ok(
                Response.success(result, "Listado de items")
        );
    }

}
