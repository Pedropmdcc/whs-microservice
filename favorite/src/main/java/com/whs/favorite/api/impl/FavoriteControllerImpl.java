package com.whs.favorite.api.impl;

import com.whs.favorite.api.controller.FavoriteController;
import com.whs.favorite.api.dto.request.FavoriteRequest;
import com.whs.favorite.api.dto.response.FavoriteResponse;
import com.whs.favorite.domain.service.FavoriteService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FavoriteControllerImpl implements FavoriteController {

    private final FavoriteService favoriteService;

    @Override
    public ResponseEntity<FavoriteResponse> save(final FavoriteRequest favoriteRequest) {
        final FavoriteResponse favoriteResponse = this.favoriteService.save(favoriteRequest);
        return new ResponseEntity<>(favoriteResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<FavoriteResponse>> getAll() {
        final List<FavoriteResponse> favoriteResponseList = this.favoriteService.getAll();
        return new ResponseEntity<>(favoriteResponseList, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<FavoriteResponse> getById(final String id) {
        final FavoriteResponse favoriteResponse = this.favoriteService.getById(id);
        return new ResponseEntity<>(favoriteResponse, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<FavoriteResponse> delete(final String itemId) {
        final FavoriteResponse favoriteResponse = this.favoriteService.delete(itemId);
        return new ResponseEntity<>(favoriteResponse, HttpStatus.NO_CONTENT);
    }
}
