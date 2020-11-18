import { Component, Input, OnInit } from '@angular/core';
import { Post } from 'src/app/model/post';
import { User } from 'src/app/model/user';
import { ApiService } from 'src/app/service/api.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {
  @Input() post: Post;
  @Input() posts: Post[];
  //posts: Post[];

  user: User = {
    idUser: null,
    username: null
  };
  showDeleteWarningVar: string = "none";
  showChangeForm: boolean = false;

  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
    this.apiService.user.subscribe(user => this.user = user);
    if (!this.user) {
      this.apiService.user.subscribe(user => this.user = user);
    }

    //this.apiService.user.subscribe(user => this.user = user);
    //this.apiService.posts.subscribe(posts => this.posts = posts);
  }

  grade(gradeNum: number) {
    const newNumberOfGrades = ++this.post.numberOfGrades;
    this.post.gradesSum += gradeNum;
    const newGradesSum = this.post.gradesSum;
    this.post.grade = newGradesSum / newNumberOfGrades;
  }

  showDeleteWarning() {
    this.showDeleteWarningVar = "block";
  }

  hideDeleteWarning() {
    this.showDeleteWarningVar = "none";
  }

  deletePost() {
    for (const p of this.posts) {
      if (p.idPost == this.post.idPost) {
        this.posts.splice(this.posts.indexOf(p), 1);
        break;
      }
    }
    this.showDeleteWarningVar = "none";

    this.apiService.deletePost(this.post.idPost);
  }

  changePost(form) {
    const changedPostContent = form.value.changePostContent;
    let changedPost: Post = null;

    for (const p of this.posts) {
      if (p.idPost == this.post.idPost) {
        p.content = changedPostContent;
        this.posts[this.posts.indexOf(p)] = p;
        changedPost = p;
        break;
      }
    }
    this.showChangeForm = false;

    this.apiService.changePost(changedPost);

  }

}
