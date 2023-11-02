package com.gdtcore.categorieservice.model.dto;

import lombok.*;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
    private int id;

    private String name;

    private String description;
}
