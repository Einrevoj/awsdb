package jcano.awspractice.controller;

import jcano.awspractice.dto.BlogDTO;
import jcano.awspractice.dto.ProductDTO;
import jcano.awspractice.model.BlogRequest;
import jcano.awspractice.model.ProductRequest;
import jcano.awspractice.service.BlogService;
import jcano.awspractice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/getAll")
    public List<BlogDTO> getAllBlogs() {
        return blogService.getAllBlogs();
    }

    @PutMapping("/add")
    public List<BlogDTO> addBlog(@RequestBody BlogRequest blogRequest) {
        return blogService.addBlog(blogRequest);
    }

    @DeleteMapping("/delete/{blogId}")
    public List<BlogDTO> deleteBlog(@PathVariable UUID blogId) {
        return blogService.deleteBlog(blogId);
    }

}
