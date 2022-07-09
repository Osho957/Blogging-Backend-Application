package com.osho.blog.services;

import java.util.List;

import com.osho.blog.payloads.PostDto;
import com.osho.blog.payloads.PostResponse;

public interface PostService {
	
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	PostDto updatePost(PostDto postDto,Integer postId);
	void deletePost(Integer postId);
	PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy);
	PostDto getPostById(Integer postId);
 
	List<PostDto> getPostByCategory(Integer categoryId);
	List<PostDto> getPostByUser(Integer userId);
	
	List<PostDto> searchPosts(String keyword);
}
