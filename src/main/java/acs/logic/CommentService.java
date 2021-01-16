package acs.logic;

import acs.boundary.CommentBoundary;

public interface CommentService {

    CommentBoundary createComment(CommentBoundary commentBoundary);
}
