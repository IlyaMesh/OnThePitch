import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PostListComponent } from './post-list/post-list.component';
import { PostFormComponent } from './post-form/post-form.component';
import {FormsModule} from "@angular/forms";
import {PostServiceService} from "./service/post-service.service";
import {HttpClientModule} from "@angular/common/http";
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { LoginComponent } from './login/login.component';
import { StandingListComponent } from './standing-list/standing-list.component';
import { ResultListComponent } from './result-list/result-list.component';

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
    ResultListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [PostServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
