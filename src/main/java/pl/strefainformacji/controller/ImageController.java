package pl.strefainformacji.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.strefainformacji.dto.response.ImageResponse;
import pl.strefainformacji.service.ImageService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {
    private final ImageService imageService;

    @GetMapping("/{id}")
    public ResponseEntity<ImageResponse> showImage(@PathVariable Long id) {
        return ResponseEntity.ok(imageService.getImage(id));
    }


}
