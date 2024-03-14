package com.rizky.lessup.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ControllerResponse<T> {
    private String status;
    private String message;
    private T data;
}
