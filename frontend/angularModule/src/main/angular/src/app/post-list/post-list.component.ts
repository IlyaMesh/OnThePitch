import { Component, OnInit } from '@angular/core';
import {Post} from "../model/post";
import {PostServiceService} from "../service/post-service.service";
import {TokenStorageService} from "../service/token-storage.service";
import {Comment} from "../model/comment";

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.css']
})
export class PostListComponent implements OnInit {

  posts: Post[];
  comments : Comment[];
  isLoggedIn = false;
  constructor(private postService:PostServiceService,private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    this.postService.findAllPosts().subscribe(data =>{this.posts = data;});
    this.postService.findAllComments().subscribe(data=>{this.comments = data;})
  }

}
