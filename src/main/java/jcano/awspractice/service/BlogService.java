package jcano.awspractice.service;

import jcano.awspractice.dto.BlogDTO;
import jcano.awspractice.dto.ProductDTO;
import jcano.awspractice.entity.BlogEntity;
import jcano.awspractice.entity.ProductEntity;
import jcano.awspractice.exception.UserAlreadyExist;
import jcano.awspractice.model.BlogRequest;
import jcano.awspractice.model.ProductRequest;
import jcano.awspractice.repository.BlogRepository;
import jcano.awspractice.util.DateTimeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final ModelMapper modelMapper;
    private final DateTimeUtil dateTimeUtil;

    public List<BlogDTO> getAllBlogs() {
        List<BlogEntity> allBlogs = blogRepository.findAll(Sort.by(Sort.Direction.ASC, "createdDate"));

        // Initialize dto
        List<BlogDTO> allBlogsDTO = new ArrayList<>();

        allBlogs.forEach(product -> {
            allBlogsDTO.add(modelMapper.map(product, BlogDTO.class));
        });

        return allBlogsDTO;
    }

    public List<BlogDTO> addBlog(BlogRequest newBlog) {

        // Save new blow to database
        blogRepository.save(BlogEntity
                .builder()
                .blogId(UUID.randomUUID())
                .blogName(newBlog.getBlogName())
                .blogAuthor(newBlog.getBlogAuthor())
                .imageLink(null)
                .description(newBlog.getDescription())
                .createdDate(dateTimeUtil.currentDate())
                .modifiedDate(dateTimeUtil.currentDate())
                .build());

        return getAllBlogs();
    }

    public List<BlogDTO> deleteBlog(UUID blogId) {

        // Get user
        BlogEntity blog = blogRepository.findByBlogId(blogId);

        // Check if user exist
        if(blog == null) throw new UserAlreadyExist("Blog doesn't exist");

        blogRepository.deleteByBlogId(blogId);

        return getAllBlogs();
    }

}
