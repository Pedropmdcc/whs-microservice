package com.whs.favorite.api.impl;

import com.whs.favorite.api.controller.FavoriteController;
import com.whs.favorite.api.dto.request.FavoriteRequest;
import com.whs.favorite.api.dto.response.FavoriteResponse;
import com.whs.favorite.domain.service.FavoriteService;
import com.whs.favorite.infrastructure.model.Favorite;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FavoriteImpl implements FavoriteController {

    private final FavoriteService favoriteService;

    @Override
    public ResponseEntity<FavoriteResponse> save(FavoriteRequest favoriteRequest) {
        Favorite favorite = favoriteService.save(favoriteRequest.requestToFavorite());
        return new ResponseEntity<>(FavoriteResponse.favoriteToResponse(favorite), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<FavoriteResponse>> getAll() {
        List<FavoriteResponse> favoriteResponseList = new ArrayList<>();
        favoriteService.getAll().forEach(e -> favoriteResponseList.add(FavoriteResponse.favoriteToResponse(e)));
        return new ResponseEntity<>(favoriteResponseList, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<FavoriteResponse> findById(String id) {
        Favorite favorite = favoriteService.findById(id);
        return new ResponseEntity<>(FavoriteResponse.favoriteToResponse(favorite), HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<FavoriteResponse> delete(String itemId) {
        Favorite favorite = favoriteService.delete(itemId);
        return new ResponseEntity<>(FavoriteResponse.favoriteToResponse(favorite), HttpStatus.NO_CONTENT);
    }
}