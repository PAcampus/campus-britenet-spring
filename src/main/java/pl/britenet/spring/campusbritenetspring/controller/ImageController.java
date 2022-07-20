package pl.britenet.spring.campusbritenetspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.campus.models.Image;
import pl.britenet.campus.services.ImageService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/image")
public class ImageController {
    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/{id}")
    public Optional<Image> getImage(@PathVariable int id) {
        return this.imageService.getImage(id);
    }

    @GetMapping
    public List<Image> getImages() {
        return this.imageService.getImages();
    }

    @PostMapping
    public void createImage(@RequestBody Image image) {
        this.imageService.insertImage(image);
    }

    @PutMapping
    public void updateImage(@RequestBody Image image) {
        this.imageService.updateImage(image);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteImage(@PathVariable int id) {
        this.imageService.deleteImage(id);
    }
}
