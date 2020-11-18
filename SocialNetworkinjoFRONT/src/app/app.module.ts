import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule }   from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TestComponent } from './comps/test/test.component';
import { NavbarComponent } from './comps/navbar/navbar.component';
import { NewPostFormComponent } from './comps/new-post-form/new-post-form.component';
import { FooterComponent } from './comps/footer/footer.component';
import { CommentsComponent } from './comps/comments/comments.component';
import { LoginComponent } from './comps/login/login.component';
import { PostComponent } from './comps/post/post.component';
import { CommentComponent } from './comps/comment/comment.component';
import { ApiService } from './service/api.service';
import { JwtInterceptor } from './service/jwt.interceptor';
import { RegisterComponent } from './comps/register/register.component';

@NgModule({
  declarations: [
    AppComponent,
    TestComponent,
    NavbarComponent,
    NewPostFormComponent,
    FooterComponent,
    CommentsComponent,
    LoginComponent,
    PostComponent,
    CommentComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    ApiService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true,
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
