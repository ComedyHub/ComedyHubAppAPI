package com.comedyhub.prot.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;  // Armazenar hash da senha

    @Column
    private String profileDescription;

    @Column
    private String profileImageUrl;

    @Column
    private String profileBannerUrl;

    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserFollower> followers = new HashSet<>();

    @OneToMany(mappedBy = "following", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserFollower> following = new HashSet<>();

    @OneToMany(mappedBy = "blocker", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserBlock> blocks = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserMemeInteraction> memeInteractions = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<MemeComment> comments = new HashSet<>();

    public User() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getProfileDescription() { return profileDescription; }
    public void setProfileDescription(String profileDescription) { this.profileDescription = profileDescription; }

    public String getProfileImageUrl() { return profileImageUrl; }
    public void setProfileImageUrl(String profileImageUrl) { this.profileImageUrl = profileImageUrl; }

    public String getProfileBannerUrl() { return profileBannerUrl; }
    public void setProfileBannerUrl(String profileBannerUrl) { this.profileBannerUrl = profileBannerUrl; }

    public Set<UserFollower> getFollowers() { return followers; }
    public void setFollowers(Set<UserFollower> followers) { this.followers = followers; }

    public Set<UserFollower> getFollowing() { return following; }
    public void setFollowing(Set<UserFollower> following) { this.following = following; }

    public Set<UserMemeInteraction> getMemeInteractions() { return memeInteractions; }
    public void setMemeInteractions(Set<UserMemeInteraction> memeInteractions) { this.memeInteractions = memeInteractions; }

    public Set<UserBlock> getBlocks() {return blocks;}
    public void setBlocks(Set<UserBlock> blocks) {this.blocks = blocks;}

    public Set<MemeComment> getComments() {return comments;}
    public void setComments(Set<MemeComment> comments) {this.comments = comments;}
}
