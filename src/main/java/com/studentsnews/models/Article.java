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
@NamedQueries({ @NamedQuery(name = "article.getAllArticles", query = "SELECT a FROM Article a"),
		@NamedQuery(name = "article.getByTitle", query = "SELECT a FROM Article a WHERE a.title =:title") })
@Entity
@XmlRootElement
@Table(name = "article")
public class Article implements Serializable {

	private static final long serialVersionUID = 1L;

	public Article() {
		super();
	}

	public Article(String title, String text, Date postDate, ArticleType articleType, String author) {
		super();
		this.title = title;
		this.text = text;
		this.postDate = postDate;
		this.articleType = articleType;
		this.author = author;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String title;

	private String text;

	private String author;

	@Temporal(TemporalType.TIMESTAMP)
	private Date postDate;

	@Enumerated(EnumType.STRING)
	private ArticleType articleType;

	private int isPublished;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public ArticleType getArticleType() {
		return articleType;
	}

	public void setArticleType(ArticleType articleType) {
		this.articleType = articleType;
	}

	public int getIsPublished() {
		return isPublished;
	}

	public void setIsPublished(int isPublished) {
		this.isPublished = isPublished;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", text=" + text + ", author=" + author + ", postDate="
				+ postDate + ", articleType=" + articleType + ", isPublished=" + isPublished + "]";
	}

}
