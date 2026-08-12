package pl.strefainformacji.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.strefainformacji.dto.request.ImageRequest;
import pl.strefainformacji.dto.response.ImageResponse;
import pl.strefainformacji.service.ImageService;

import java.net.URI;

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
    public ResponseEntity<ImageResponse> saveImage(@RequestBody ImageRequest imageRequest) {
        ImageResponse savedImage = imageService.saveImage(imageRequest);
        return ResponseEntity.created(URI.create("/image/" + savedImage.imageId()))
                .body(savedImage);
    }

    @PutMapping
    public ResponseEntity<ImageResponse> updateImage(@RequestBody ImageRequest imageRequest) {
        return ResponseEntity.ok(imageService.updateImage(imageRequest));
    }


}
