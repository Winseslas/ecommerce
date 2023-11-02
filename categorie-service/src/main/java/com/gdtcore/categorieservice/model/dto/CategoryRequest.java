package com.gdtcore.categorieservice.model.dto;

import lombok.*;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {
    private String name;

    private String description;
}
