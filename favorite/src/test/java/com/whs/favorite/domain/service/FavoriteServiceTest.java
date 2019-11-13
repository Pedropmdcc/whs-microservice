package com.whs.favorite.domain.service;

import com.whs.favorite.api.dto.request.FavoriteRequest;
import com.whs.favorite.api.dto.response.FavoriteResponse;
import com.whs.favorite.infrastructure.model.Favorite;
import com.whs.favorite.infrastructure.repository.FavoriteRepository;
import com.whs.favorite.util.FavoriteTestDataProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;

public class FavoriteServiceTest {

    /*@Mock
    private FavoriteRepository favoriteRepositoryMock;
    @InjectMocks
    private FavoriteService favoriteServiceMock;
    @BeforeEach
    public void setUp() { MockitoAnnotations.initMocks(this); }

    @Test
    void save() {
        //Given
        final FavoriteRequest favoriteRequest = FavoriteTestDataProvider.getFavoriteRequest();
        //When
        when(favoriteRepositoryMock.save(any(Favorite.class))).thenReturn(FavoriteTestDataProvider.getFavorite());
        final ResponseEntity<FavoriteResponse> favoriteResponse = favoriteServiceMock.save(favoriteRequest);
        //Then
        assertEquals(favoriteRequest.getName(), favoriteResponse.getBody().getName());
        assertEquals(favoriteRequest.getItemId(), favoriteResponse.getBody().getItemId());
    }

    @Test
    void findById() {
        //Given

        //When

        //Then
    }

    @Test
    void getAll() {
        //Given
        final Favorite favorite = FavoriteTestDataProvider.getFavorite();
        final List<Favorite> favoriteList = new ArrayList<>();
        favoriteList.add(favorite);
        //When
        when(favoriteRepositoryMock.findAll()).thenReturn(favoriteList);
        //Then
    }

    @Test
    void delete() {
        //Given

        //When

        //Then
    }*/
}
