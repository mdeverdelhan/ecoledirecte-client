package eu.verdelhan.ecoledirecte;

import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;

public record EcoleDirecteApiResponse<T>(
        @Expose Long code,
        @Expose String host,
        @Expose T data,
        JsonElement rawData,
        @Expose String message,
        @Expose String token
) {
}
