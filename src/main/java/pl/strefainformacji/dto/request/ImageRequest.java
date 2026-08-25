package pl.strefainformacji.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ImageRequest {
    private Long imageId;
    @NotBlank
    private String srcImg;
    @NotBlank
    private String altImg;
    @NotNull
    private Long articleId;
}