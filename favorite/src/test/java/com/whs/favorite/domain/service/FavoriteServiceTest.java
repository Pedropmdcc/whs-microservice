package com.whs.favorite.domain.service;

import com.whs.favorite.api.controller.FavoriteController;
import com.whs.favorite.api.dto.error.DeleteBadRequestException;
import com.whs.favorite.api.dto.error.NotFoundException;
import com.whs.favorite.api.dto.request.FavoriteRequest;
import com.whs.favorite.api.dto.response.FavoriteResponse;
import com.whs.favorite.infrastructure.model.Favorite;
import com.whs.favorite.infrastructure.repository.FavoriteRepository;
import com.whs.favorite.util.FavoriteTestDataProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.hateoas.Link;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


class FavoriteServiceTest {

    @Mock
    private FavoriteRepository favoriteRepository;

    @InjectMocks
    private FavoriteService favoriteService;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    void saveSuccess() {

        //Given
        final FavoriteRequest favoriteRequest = FavoriteTestDataProvider.getFavoriteRequest();
        final Link link = linkTo(methodOn(FavoriteController.class).save(favoriteRequest)).withSelfRel();

        //When
        when(this.favoriteRepository.save(any(Favorite.class))).thenReturn(favoriteRequest.requestToFavorite());
        final FavoriteResponse favoriteResponse = this.favoriteService.save(favoriteRequest);
        favoriteResponse.add(link);

        //Then
        assertEquals(favoriteRequest.getName(), favoriteResponse.getName());
        assertEquals(favoriteRequest.getItemId(), favoriteResponse.getItemId());
        assertEquals(favoriteRequest.getHref(), favoriteResponse.getHref());
    }

    @Test
    void addNotSuccess() {

        //Given
        final FavoriteRequest favoriteRequest = FavoriteTestDataProvider.getFavoriteRequest();
        final Favorite favorite = new Favorite();
        favorite.setName("WrongName");

        //When
        when(this.favoriteRepository.save(any(Favorite.class))).thenReturn(favorite);
        final FavoriteResponse favoriteResponse = this.favoriteService.save(favoriteRequest);

        //Then
        assertNotEquals(favoriteRequest.getName(), favoriteResponse.getName());
    }

    @Test
    void getAll() {

        //Given
        final Favorite favorite = FavoriteTestDataProvider.getFavorite();

        final List<Favorite> favoriteList = new ArrayList<>();
        favoriteList.add(favorite);

        //When
        when(this.favoriteRepository.findAll()).thenReturn(favoriteList);
        final List<FavoriteResponse> favoriteResponseList = this.favoriteService.getAll();

        //Then
        assertEquals(favorite.getName(), favoriteResponseList.get(0).getName());
    }

    @Test
    void getById() {

        //Given
        final FavoriteRequest favoriteRequest = FavoriteTestDataProvider.getFavoriteRequest();
        final Favorite favorite = FavoriteTestDataProvider.getFavorite();
        favorite.setName(favoriteRequest.getName());
        favorite.setId(favoriteRequest.getItemId());
        final FavoriteResponse favoriteResponse = FavoriteResponse.favoriteToResponse(favorite);
        final Link link = linkTo(methodOn(FavoriteController.class).getById(favorite.getId())).withSelfRel();
        favoriteResponse.add(link);

        //When
        when(this.favoriteRepository.findById(favorite.getId())).thenReturn(Optional.of(favorite));

        //Then
        assertEquals(favoriteResponse, this.favoriteService.getById(favorite.getId()));
        assertEquals(favoriteResponse.getResponseId(), this.favoriteService.getById(favorite.getId()).getResponseId());
        assertThrows(NotFoundException.class, () -> this.favoriteService.getById("10"));
    }

    @Test
    void delete() {

        //Given
        final FavoriteRequest favoriteRequest = FavoriteTestDataProvider.getFavoriteRequest();
        final Favorite favorite = FavoriteTestDataProvider.getFavorite();
        favorite.setItemId(favoriteRequest.getItemId());
        final List<Favorite> favoriteList = new ArrayList<>();
        favoriteList.add(favorite);

        //When
        when(this.favoriteRepository.findByItemId(favoriteRequest.requestToFavorite().getItemId())).thenReturn(Optional.of(favoriteList.get(0)));


        //Then
        assertEquals(FavoriteResponse.favoriteToResponse(favoriteList.get(0)), this.favoriteService.delete(favoriteRequest.requestToFavorite().getItemId()));
        assertThrows(DeleteBadRequestException.class, () -> this.favoriteService.delete("10"));

    }
}
