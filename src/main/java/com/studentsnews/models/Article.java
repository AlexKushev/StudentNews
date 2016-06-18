package com.studentsnews.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import com.studentsnews.enums.ArticleType;

/**
 * Entity implementation class for Entity: Article
 *
 */
@NamedQueries({ @NamedQuery(name="article.getAllArticles", query="SELECT a FROM Article a")})
@Entity
@XmlRootElement
public class Article implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Article() {
		super();
	}
	
	public Article(String title, String text, Date postDate, ArticleType articleType) {
		super();
		this.title = title;
		this.text = text;
		this.postDate = postDate;
		this.articleType = articleType;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String title;
	
	private String text;
	
	@ManyToOne(cascade = {CascadeType.PERSIST})
	@JoinColumn(name = "user_fk")
	private User author;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date postDate;
	
	@Enumerated(EnumType.STRING)
	private ArticleType articleType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setHeader(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public ArticleType getArticleType() {
		return articleType;
	}

	public void setArticleType(ArticleType articleType) {
		this.articleType = articleType;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", header=" + title + ", text=" + text + ", author=" + author + ", postDate="
				+ postDate + ", articleType=" + articleType + "]";
	}

}
