import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './comps/login/login.component';
import { RegisterComponent } from './comps/register/register.component';
import { TestComponent } from './comps/test/test.component';


const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: '', component: TestComponent },
  { path: 'register', component: RegisterComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
