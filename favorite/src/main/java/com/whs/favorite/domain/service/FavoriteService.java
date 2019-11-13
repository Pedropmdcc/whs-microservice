package com.whs.favorite.domain.service;

import com.mongodb.MongoWriteConcernException;
import com.whs.favorite.api.controller.FavoriteController;
import com.whs.favorite.api.dto.error.DeleteBadRequestException;
import com.whs.favorite.api.dto.error.DuplicateResourceException;
import com.whs.favorite.api.dto.error.NotFoundException;
import com.whs.favorite.api.dto.request.FavoriteRequest;
import com.whs.favorite.api.dto.response.FavoriteResponse;
import com.whs.favorite.api.impl.FavoriteImpl;
import com.whs.favorite.infrastructure.model.Favorite;
import com.whs.favorite.infrastructure.repository.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private static final Logger LOGGER = LogManager.getLogger(FavoriteImpl.class.getName());

    /**
     * Save new Favorite in the database.
     * @param favorite Favorite Request received as input.
     * @return ResponseEntity with status.
     */
    public Favorite save(Favorite favorite){
        LOGGER.info("Adding new favorite " + favorite.getName() + " in the database");
        try{
            /*String favoriteId = favorite.getFavoriteId();
            Link Link = linkTo(methodOn(FavoriteController.class).findById(favoriteId)).withSelfRel();
            favorite.add(Link);*/
            return favoriteRepository.save(favorite);
        }
        catch (Exception ex)
        {
            throw new DuplicateResourceException(ex.getLocalizedMessage());
        }
    }

    /**
     * Search for Favorite with give id in database.
     * @param id Favorite ID.
     * @return Response with HTTP status.
     */
    public Favorite findById(String id){
        LOGGER.info("Searching for favorite with id -> " + id);
        Favorite favorite = favoriteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        /*Link link = linkTo(methodOn(FavoriteController.class).findById(id)).withSelfRel();
        favorite.add(link);*/
        return favorite;
    }

    /**
     * Returns all the Favorites in database.
     * @return Response with HTTP status.
     */
    public List<Favorite> getAll(){
        LOGGER.info("Getting all Favorites");
        try{
            List<Favorite> favoriteList = new ArrayList<>(favoriteRepository.findAll());
            //List<Favorite> favoriteList = favoriteRepository.findAll();
            /*for(final Favorite favorite : favoriteList){
                String favoriteResponseId = favorite.getFavoriteId();
                Link selfLink = linkTo(methodOn(FavoriteController.class).findById(favoriteResponseId)).withSelfRel();
                favorite.add((selfLink));
            }*/
            return favoriteList;
        }
        catch (MongoWriteConcernException ex){
            throw ex;
        }
    }

    /**
     * Delete favorite from database
     * @param itemId Favorite ID
     * @return Response with HTTP status.
     */
    public Favorite delete(String itemId){
        LOGGER.info("Deleting favorite with itemId -> " + itemId);
        Favorite favorite = favoriteRepository.findByItemId(itemId).orElseThrow(() -> new DeleteBadRequestException(itemId));
        favoriteRepository.delete(favorite);
        /*String favoriteId = favorite.getFavoriteId();
        Link link = linkTo(methodOn(FavoriteController.class).findById(favoriteId)).withSelfRel();
        favorite.add(link);*/
        return favorite;
    }
}