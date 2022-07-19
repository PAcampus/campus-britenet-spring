package pl.britenet.spring.campusbritenetspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.britenet.campus.models.Image;
import pl.britenet.campus.services.ImageService;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/image")
public class ImageController {
//    private final ImageService imageService;
//
//    @Autowired
//    public ImageController(ImageService imageService) {
//        this.imageService = imageService;
//    }
//
//    @GetMapping("/{id}")
//    public Optional<Image> getImage(@PathVariable int id) {
//        return this.imageService(id);
//    }
//
//    @GetMapping
//    public List<> get() {
//        return this.Service.get();
//    }
//
//    @PostMapping
//    public void create(@RequestBody T t) {
//        this.Service.insert(t);
//    }
//
//    @PutMapping
//    public void update(@RequestBody T t) {
//        this.Service.update(t);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public void delete(@PathVariable int id) {
//        this.Service.delete(id);
//    }
}
