import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {PostListComponent} from "./post-list/post-list.component";
import {PostFormComponent} from "./post-form/post-form.component";
import {LoginComponent} from "./login/login.component";
import {StandingListComponent} from "./standing-list/standing-list.component";
import {ResultServiceService} from "./service/result-service.service";
import {ResultListComponent} from "./result-list/result-list.component";


const routes: Routes = [
  {path: '',component: ResultListComponent},
  {path: 'posts', component: PostListComponent},
  {path: 'matches',component:ResultListComponent},
  {path: 'addPost', component: PostFormComponent},
  {path: 'login',component: LoginComponent},
  {path: 'standings',component: StandingListComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }