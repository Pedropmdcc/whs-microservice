package com.whs.favorite.api.controller;

import com.whs.favorite.api.dto.request.FavoriteRequest;
import com.whs.favorite.api.dto.response.FavoriteResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@CrossOrigin
public interface FavoriteController {

    /**
     * Add Favorite to the database
     * @param favorite Favorite object with data
     * @return Response with HTTP Status.
     */
    @PostMapping("/save")
    ResponseEntity<FavoriteResponse> save(@RequestBody final FavoriteRequest favorite);

    /**
     * Returns all Favorites.
     * @return Response with a List of all Favorites.
     */
    @GetMapping("/all")
    ResponseEntity<List<FavoriteResponse>> getAll();

    /**
     * Search for a Favorite with specific href.
     * @param id of the Favorite.
     * @return ResponseEntity with Response Status.
     */
    @GetMapping("/search/{id}")
    ResponseEntity<FavoriteResponse> getById(@PathVariable("id") final String id);

    /**
     * Delete Favorite from the database
     * @param itemId of the Favorite
     * @return ResponseEntity with Response Status.
     */
    @DeleteMapping("/delete/{itemId}")
    ResponseEntity<FavoriteResponse> delete(@PathVariable("itemId") final String itemId);
}
