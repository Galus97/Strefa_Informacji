package pl.strefainformacji.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.strefainformacji.dto.request.ImageRequest;
import pl.strefainformacji.dto.response.ImageResponse;
import pl.strefainformacji.service.ImageService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {
    private final ImageService imageService;

    @GetMapping("/{id}")
    public ResponseEntity<ImageResponse> showImage(@PathVariable Long id) {
        return ResponseEntity.ok(imageService.getImage(id));
    }

    @PostMapping
    public ResponseEntity<ImageResponse> saveImage(@RequestBody @Valid ImageRequest imageRequest) {
        ImageResponse savedImage = imageService.saveImage(imageRequest);
        return ResponseEntity.created(URI.create("/image/" + savedImage.imageId()))
                .body(savedImage);
    }

    @PutMapping
    public ResponseEntity<ImageResponse> updateImage(@RequestBody @Valid ImageRequest imageRequest) {
        return ResponseEntity.ok(imageService.updateImage(imageRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
        imageService.deleteImage(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all/article/{id}")
    public ResponseEntity<List<ImageResponse>> showAllArticleImages(@PathVariable Long id) {
        return ResponseEntity.ok(imageService.getAllImagesByArticle(id));
    }
}
