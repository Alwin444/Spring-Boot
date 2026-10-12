package com.demo.sec9.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String first_name;
    @Column(nullable = false)
    private String last_name;
    @Column(nullable = false,unique = true)
    private String email;

    //OneToMany: Annotation which defines OneToMany reationship.
    //mappedBy = "user": This defines Do not create an extra table or column for this side. The field named 'user' inside the 'Post' class is the owner and already manages the foreign key.
    //cascade = CascadeType.ALL: It tells Hibernate to automatically propagate operations performed on a parent entity to its associated child entities. CascadeType.ALL: Applies all cascade operations (PERSIST, MERGE, REMOVE, REFRESH, DETACH).
    //orphanRemoval = true: tells Hibernate to automatically delete a child entity from the database whenever it is removed from the parent's collection. While CascadeType.REMOVE only deletes children when the parent entity itself is deleted, orphanRemoval handles the scenario where the parent stays, but a child is detached from it.
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Post> posts;

    public User() {
    }

    public User(Long id, String first_name, String last_name, String email) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}

