import { Component, Input, OnInit } from '@angular/core';
import { Comment } from 'src/app/model/comment';
import { User } from 'src/app/model/user';
import { ApiService } from 'src/app/service/api.service';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {
  @Input() comment: Comment;
  @Input() comments: Comment[];
  showDeleteWarningVar: string = "none";
  showChangeForm: boolean = false;
  user: User = {
    idUser: null,
    username: null
  }

  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
    this.apiService.user.subscribe(user => this.user = user);
    if (!this.user) {
      
      
      //this.apiService.getUser();
      //this.apiService.user.subscribe(user => this.user = user);
    }
    //this.user = this.apiService.user;
    
    //this.apiService.user.subscribe(user => this.user = user);
    
  }

  showDeleteWarning() {
    this.showDeleteWarningVar = "block";
  }

  hideDeleteWarning() {
    this.showDeleteWarningVar = "none";
  }

  deleteComment() {
    this.comments.splice(this.comments.indexOf(this.comment), 1);
    this.showDeleteWarningVar = "none";
    
    this.apiService.deleteComment(this.comment.idPostComment);
  }

  changeComment(form) {
    const changedCommentContent = form.value.changeCommentContent;
    let changedComment: Comment = null;

    for (const c of this.comments) {
      if (c.idPostComment == this.comment.idPostComment) {
        c.content = changedCommentContent;
        this.comments[this.comments.indexOf(c)] = c;
        changedComment = c;
        break;
      }
    }
    this.showChangeForm = false;

    this.apiService.changeComment(changedComment);
  }

}