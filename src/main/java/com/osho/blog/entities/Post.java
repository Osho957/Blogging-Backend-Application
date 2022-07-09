package com.osho.blog.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "post")
public class Post {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer postId;
	@Column(name="post_title", nullable =false,length=100)
	private String title;
	@Column(name="post_content", nullable =false,length=1000)
	private String content;
	@Column(name="post_image")
	private String imageName;
	@Column(name="post_date", nullable =false)
	private Date addDate;
	
	@ManyToOne
	@JoinColumn(name ="category_id")
	private Category category;
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
	private Set<Comment> comments = new HashSet<>();

}
