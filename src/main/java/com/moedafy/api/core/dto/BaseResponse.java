package com.moedafy.api.core.dto;

public record BaseResponse<T>(boolean success, T object) {
}