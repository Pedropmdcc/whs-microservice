package com.whs.favorite.api.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.whs.favorite.infrastructure.model.Favorite;
import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * Class that represents the Customer request.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteRequest {
    @NotNull(message = "No itemId provided")
    private String itemId;
    @NotNull(message = "No name provided")
    private String name;
    @NotNull(message = "No iconClass provided")
    private String iconClass;
    @NotNull(message = "No href provided")
    private String href;

    public Favorite requestToFavorite(){
        return Favorite.builder()
                .itemId(this.getItemId())
                .name(this.getName())
                .iconClass(this.getIconClass())
                .href(this.getHref())
                .build();
    }
}