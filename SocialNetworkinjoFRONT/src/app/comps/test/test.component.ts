import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Comment } from 'src/app/model/comment';
import { Post } from 'src/app/model/post';
import { User } from 'src/app/model/user';
import { ApiService } from 'src/app/service/api.service';

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css']
})
export class TestComponent implements OnInit {
  posts: Post[] = [];
  user: User;
  firstTimeNumberOfPostsCheck: boolean = true;
  updatePostsVar: boolean = false;
  numberOfPosts: number;

  constructor(private apiService: ApiService, private router: Router) { }

  ngOnInit(): void {
    if(!localStorage.getItem("jwt")) {
      this.router.navigateByUrl("/login");
    }
    
    if (!this.user) {
      this.apiService.user.subscribe(user => this.user = user);
    }

    this.apiService.numberOfPosts.subscribe(numberOfPosts => {
      if(this.firstTimeNumberOfPostsCheck) {
        this.numberOfPosts = numberOfPosts;
        this.firstTimeNumberOfPostsCheck = false;
      }
      if(numberOfPosts > this.posts.length) {
        console.log("MORE POSTS ON THE SERVER!!!");
        console.log("Client posts = " + this.numberOfPosts);
        console.log("Server posts = " + numberOfPosts);
        this.numberOfPosts = numberOfPosts;
        this.updatePostsVar = true;
      }
      else {
        console.log("NO NEW POSTS!!!");
      }
    })

    //this.user = this.apiService.user;
    this.apiService.getAllPosts().subscribe(posts => this.posts = posts);
  }

  addNewPost(newPostContent) {
    const newPost: Post = {
      idPost: null,
      content: newPostContent,
      createdOn: new Date(),
      numberOfComments: 0,
      grade: 0,
      numberOfGrades: 0,
      gradesSum: 0,
      creatorUsername: this.user.username,
      comments: []
    }
    this.apiService.addPost(newPost).subscribe(addedPost => {
      this.posts.unshift(addedPost)
      this.numberOfPosts++;
    })


  }

  addComment(form: NgForm, post: Post) {
    const commentContent = form.value.newCommentContent;
    form.reset();

    const newComment: Comment = {
      idPostComment: null,
      content: commentContent,
      createdOn: new Date(),
      creatorUsername: this.user.username
    }

    const postArrayIndex = this.posts.indexOf(post);
    this.apiService.addComment(newComment, this.posts[postArrayIndex].idPost).subscribe(addedComment => {
      post.comments.push(addedComment);
      post.numberOfComments++;
      this.posts[postArrayIndex] = post;
    })
  }

  updatePosts() {
    this.apiService.getAllPosts().subscribe(posts => this.posts = posts);
    this.updatePostsVar = false;
  }

}