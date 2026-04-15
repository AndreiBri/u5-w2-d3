package andreibri.u5_w2_d3.services;

import andreibri.u5_w2_d3.entities.Author;
import andreibri.u5_w2_d3.entities.BlogPost;
import andreibri.u5_w2_d3.payloads.BlogPostRequest;
import andreibri.u5_w2_d3.repository.AuthorRepository;
import andreibri.u5_w2_d3.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BlogPostService {

    @Autowired
    private BlogPostRepository blogPostRepo;

    @Autowired
    private AuthorRepository authorRepo;

    public BlogPost create(BlogPostRequest req) {

        Author author = authorRepo.findById(req.authorId)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        BlogPost post = new BlogPost();
        post.setCategory(req.category);
        post.setTitle(req.title);
        post.setContent(req.content);
        post.setReadingTime(req.readingTime);
        post.setCover("https://picsum.photos/200/300");
        post.setAuthor(author);

        return blogPostRepo.save(post);
    }

    public List<BlogPost> findAll() {
        return blogPostRepo.findAll();
    }

    public BlogPost findById(UUID id) {
        return blogPostRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog post not found"));
    }

    public void deleteById(UUID id) {
        blogPostRepo.deleteById(id);
    }
}
