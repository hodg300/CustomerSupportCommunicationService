package acs.rest;
import acs.logic.utils.FilterType;
import acs.utils.SortOrder;
import acs.boundary.CommentBoundary;
import acs.logic.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //GET


    //POST
    // Create COMMENT
    @RequestMapping(path = "/comment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommentBoundary createComment(@RequestBody CommentBoundary commentBoundary) {
        return this.commentService.createComment(commentBoundary);
    }


}
