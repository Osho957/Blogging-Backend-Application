package com.osho.blog.services;

import com.osho.blog.payloads.CommentDto;

public interface CommentService {

	
	CommentDto createComment(CommentDto commenDto,Integer postId);
	void deleteComment(Integer commentId);
	
}
