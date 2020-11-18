import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Post } from 'src/app/model/post';

@Component({
  selector: 'app-new-post-form',
  templateUrl: './new-post-form.component.html',
  styleUrls: ['./new-post-form.component.css']
})
export class NewPostFormComponent implements OnInit {
  @Output() newPostEmitter = new EventEmitter<string>();

  constructor() { }

  ngOnInit(): void {
  }

  onSubmit(form: NgForm) {
    const postContent = form.value.newPostContent;
    form.reset();
    this.newPostEmitter.emit(postContent);
  }

}
