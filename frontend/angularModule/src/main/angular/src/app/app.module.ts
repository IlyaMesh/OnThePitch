import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {PostListComponent} from './post-list/post-list.component';
import {PostFormComponent} from './post-form/post-form.component';
import {FormsModule} from "@angular/forms";
import {PostServiceService} from "./service/post-service.service";
import {HttpClientModule} from "@angular/common/http";
import {HeaderComponent} from './header/header.component';
import {FooterComponent} from './footer/footer.component';
import {LoginComponent} from './login/login.component';
import {StandingListComponent} from './standing-list/standing-list.component';
import {ResultListComponent} from './result-list/result-list.component';
import {ClubViewComponent} from './club-view/club-view.component';
import {RegisterComponent} from './register/register.component';
import {ProfileComponent} from './profile/profile.component';
import {BoardUserComponent} from './board-user/board-user.component';
import {authInterceptorProviders} from "./helpers/auth.interceptor";
import {UploadFilesComponent} from './upload-files/upload-files.component';
import {PostDetailComponent} from './post-detail/post-detail.component';
import {CommentFormComponent} from './comment-form/comment-form.component';
import {NgxPaginationModule} from "ngx-pagination";
import { AdminBoardComponent } from './admin-board/admin-board.component';
import { LogListComponent } from './log-list/log-list.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    PostListComponent,
    PostFormComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    StandingListComponent,
    ResultListComponent,
    ClubViewComponent,
    RegisterComponent,
    ProfileComponent,
    BoardUserComponent,
    UploadFilesComponent,
    PostDetailComponent,
    CommentFormComponent,
    AdminBoardComponent,
    LogListComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgxPaginationModule
  ],
  providers: [PostServiceService,authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
