import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, interval } from 'rxjs';
import { share, flatMap, switchMap, map } from 'rxjs/operators';
import { Comment } from '../model/comment';
import { Post } from '../model/post';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  numberOfPosts: Observable<number>;

  user: Observable<User>;
  //user: User;
  posts: Observable<Post[]>;

  constructor(private http: HttpClient, private router: Router) {
    this.getUser();
    this.getNumberOfPosts();
  }

  getAllPosts(): Observable<Post[]> {
    /*const headers = {
      "Authorization": "Bearer " + localStorage.getItem("jwt")
    }
    console.log("GET POSTS HEADERS = " + headers.Authorization);
    */
    //return this.http.get<Post[]>("/SNTiac/api/getPosts", { headers });
    return this.http.get<Post[]>("/SNTiac/api/getPosts");
  }

  /*getAllPosts() {
    const headers = {
      "Authorization": "Bearer " + localStorage.getItem("jwt")
    }

    this.posts = this.http.get<Post[]>("/SNTiac/api/getPosts", { headers });
  }*/


  login(username: string, password: string): number {
    this.http.post<any>("/SNTiac/api/authenticate", { username, password }).subscribe(jwt => {
      localStorage.setItem("jwt", jwt.jwt)
      this.getUser();
      this.getAllPosts();
      this.router.navigateByUrl("");
      return 200;
    },
    error => {
      return error.status;
    });
    return 401;
  }

  register(username: string, password: string) {
    this.http.post<any>("/SNTiac/api/register", { username, password }).subscribe(success => {
      if(success) {
        this.login(username, password);
      }
      else {
        this.router.navigateByUrl("/register?success=" + success);
      }
    });
  }

  /*
  getUser() {
    this.http.get<User>("/SNTiac/api/getUser").subscribe(user => this.user = user);
  }
  */

  getUser() {
    this.user = this.http.get<User>("/SNTiac/api/getUser").pipe(
      share()
    );
  }

  addComment(comment: Comment, idPost: number): Observable<Comment> {
    return this.http.post<any>("/SNTiac/api/addComment/" + idPost, comment);
  }

  addPost(post: Post): Observable<Post> {
    return this.http.post<Post>("/SNTiac/api/addPost", post);
  }

  changePost(post: Post) {
    this.http.patch<any>("/SNTiac/api/changePost", post).subscribe();
  }

  changeComment(comment: Comment) {
    this.http.patch<any>("/SNTiac/api/changeComment", comment).subscribe();
  }

  deletePost(idPost: number) {
    this.http.delete<any>("/SNTiac/api/deletePost/" + idPost).subscribe();
  }

  deleteComment(idComment: number) {
    this.http.delete<any>("/SNTiac/api/deleteComment/" + idComment).subscribe();
  }

  updatePosts() {
    console.log("UPDATE POSTS HAS BEEN RAN");
    
    this.posts = interval(10000).pipe(
      switchMap((x) => this.http.get<Post[]>("/SNTiac/api/getPosts")),
      share()
    )
  }

  getNumberOfPosts() {
    this.numberOfPosts = interval(10000).pipe(
      switchMap((x) => this.http.get<number>("/SNTiac/api/getNumberOfPosts")),
      share()
    )
  }


}
