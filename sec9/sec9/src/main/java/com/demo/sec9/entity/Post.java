package com.demo.sec9.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 100)
    private String title;
    @Column(nullable = false,length = 500)
    private String description;
    @Column(nullable = false,columnDefinition = "TEXT")
    private String content;
    //ManyToOne defines Many-to-One Relationship, In JPA, the @ManyToOne side is always the owning side of the relationship, meaning this class controls the foreign key value in the database.
    @ManyToOne(fetch = FetchType.LAZY)
    //The below code name the foreign key column as user_id
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Post() {
    }

    public Post(Long id, String title, String description, String content, User user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
