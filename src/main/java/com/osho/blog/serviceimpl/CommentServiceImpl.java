package com.osho.blog.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osho.blog.entities.Comment;
import com.osho.blog.entities.Post;
import com.osho.blog.exceptions.ResourceNotFoundException;
import com.osho.blog.payloads.CommentDto;
import com.osho.blog.repositories.CommentRepo;
import com.osho.blog.repositories.PostRepo;
import com.osho.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commenDto, Integer postId) {
		// TODO Auto-generated method stub
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId));
	    Comment comment =this.modelMapper.map(commenDto, Comment.class);
		comment.setPost(post);
		Comment savedComment = this.commentRepo.save(comment);
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		// TODO Auto-generated method stub
       Comment comment = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "comment id", commentId));
       this.commentRepo.delete(comment);
	}

}
