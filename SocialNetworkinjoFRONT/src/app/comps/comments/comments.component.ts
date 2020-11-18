import { Component, Input, OnInit } from '@angular/core';
import { Comment } from 'src/app/model/comment';
import { User } from 'src/app/model/user';
import { ApiService } from 'src/app/service/api.service';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit {
  showCommentsVar: string = "block";
  @Input() comments: Comment[];
  user: User = {
    idUser: null,
    username: null
  }

  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
    if (!this.user) {
      this.apiService.user.subscribe(user => this.user = user);
    }

    //this.apiService.user.subscribe(user => this.user = user);
  }

  showComments() {
    if (this.showCommentsVar == "block") {
      this.showCommentsVar = "none";
    }
    else {
      this.showCommentsVar = "block";
    }
  }

  

}
