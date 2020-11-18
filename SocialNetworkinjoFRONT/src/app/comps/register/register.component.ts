import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from 'src/app/service/api.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  success: string;

  constructor(private apiService: ApiService, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => this.success = params.success);
  }

  register(form) {
    const username = form.value.username;
    const password = form.value.password;

    this.apiService.register(username, password);
  }

}
