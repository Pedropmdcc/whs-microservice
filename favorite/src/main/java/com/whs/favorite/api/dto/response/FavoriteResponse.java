package com.whs.favorite.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.whs.favorite.infrastructure.model.Favorite;
import lombok.*;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavoriteResponse {
    private String id;
    private String itemId;
    private String name;
    private String iconClass;
    private String href;

    public static FavoriteResponse favoriteToResponse(Favorite favorite){
        return FavoriteResponse.builder()
                .id(favorite.getId())
                .itemId(favorite.getItemId())
                .name(favorite.getName())
                .iconClass(favorite.getIconClass())
                .href(favorite.getHref())
                .build();
    }
}