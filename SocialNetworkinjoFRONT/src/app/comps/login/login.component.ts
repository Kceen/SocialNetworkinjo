import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ApiService } from 'src/app/service/api.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  failedLogin: boolean = false;

  constructor(private apiService: ApiService, private router: Router) { }

  ngOnInit(): void {
  }

  login(form) {
    const username = form.value.username;
    const password = form.value.password;
  
    if(this.apiService.login(username, password) == 401) {
      this.failedLogin = true;
    }
  }

}
