package com.whs.favorite.util;

import com.whs.favorite.api.dto.request.FavoriteRequest;
import com.whs.favorite.infrastructure.model.Favorite;

public class FavoriteTestDataProvider {
    public static FavoriteRequest getFavoriteRequest(){
        return FavoriteRequest.builder()
                .itemId("itemIdMock")
                .name("nameMock")
                .iconClass("iconClassMock")
                .href("hrefMock")
                .build();
    }

    public static Favorite getFavorite(){
        return Favorite.builder()
                .id("idMock2")
                .itemId("itemIdMock2")
                .name("nameMock2")
                .iconClass("iconClassMock2")
                .href("hrefMock2")
                .build();
    }
}