package com.whs.favorite.domain.service;

import com.whs.favorite.api.controller.FavoriteController;
import com.whs.favorite.api.dto.error.DeleteBadRequestException;
import com.whs.favorite.api.dto.error.NotFoundException;
import com.whs.favorite.api.dto.request.FavoriteRequest;
import com.whs.favorite.api.dto.response.FavoriteResponse;
import com.whs.favorite.infrastructure.model.Favorite;
import com.whs.favorite.infrastructure.repository.FavoriteRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 Favorite Service.
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;

    /**
     * Save new Favorite in the database.
     * @param favoriteRequest Favorite Request received as input.
     * @return ResponseEntity with status.
     */
    public FavoriteResponse save(final FavoriteRequest favoriteRequest){
        log.info("Adding new favorite " + favoriteRequest.getName() + " in the database");

        final FavoriteResponse favoriteResponse = FavoriteResponse.favoriteToResponse(
                this.favoriteRepository.save(favoriteRequest.requestToFavorite()));
        final Link Link = linkTo(methodOn(FavoriteController.class).save(favoriteRequest)).withSelfRel();
        favoriteResponse.add(Link);
        return favoriteResponse;
    }

    /**
     * Returns all the Favorites in database.
     * @return Response with HTTP status.
     */
    public List<FavoriteResponse> getAll(){
        log.info("Getting all Favorites");

        final List<FavoriteResponse> favoriteResponseList = new ArrayList<>();
        this.favoriteRepository.findAll().forEach(
                f -> favoriteResponseList.add(FavoriteResponse.favoriteToResponse(f)));

        for (final FavoriteResponse favoriteResponse : favoriteResponseList) {
            String favoriteResponseId = favoriteResponse.getResponseId();
            Link selfLink = linkTo(methodOn(FavoriteController.class).getById(favoriteResponseId)).withSelfRel();
            favoriteResponse.add((selfLink));
        }
        return favoriteResponseList;
    }

    /**
     * Search for Favorite with give id in database.
     * @param id Favorite ID.
     * @return Response with HTTP status.
     */
    public FavoriteResponse getById(final String id){
        log.info("Searching for favorite with id -> " + id);
        final Favorite favorite = this.favoriteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        final FavoriteResponse favoriteResponse = FavoriteResponse.favoriteToResponse(favorite);
        final Link link = linkTo(methodOn(FavoriteController.class).getById(id)).withSelfRel();
        favoriteResponse.add(link);
        return favoriteResponse;
    }

    /**
     * Delete favorite from database
     * @param itemId Favorite ID
     * @return Response with HTTP status.
     */
    public FavoriteResponse delete(final String itemId){
        log.info("Deleting favorite with itemId -> " + itemId);
        final Favorite favorite = this.favoriteRepository.findByItemId(itemId).orElseThrow(() -> new DeleteBadRequestException(itemId));
        this.favoriteRepository.delete(favorite);
        final FavoriteResponse favoriteResponse = FavoriteResponse.favoriteToResponse(favorite);
        return favoriteResponse;
    }
}
