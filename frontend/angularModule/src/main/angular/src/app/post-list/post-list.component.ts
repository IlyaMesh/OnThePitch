import { Component, OnInit } from '@angular/core';
import {Post} from "../model/post";
import {PostServiceService} from "../service/post-service.service";
import {TokenStorageService} from "../service/token-storage.service";
import {Comment} from "../model/comment";
import {PagePost} from "../model/page-post";

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.css']
})
export class PostListComponent implements OnInit {

  posts: Post[];
  postPage: PagePost;
  selectedPage : number = 0;
  size:number = 5;
  page:number = 1;
  isLoggedIn = false;
  constructor(private postService:PostServiceService,private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    this.getPagePost(0);

  }
  onSelect(page: number): void {
    console.log("selected page : "+page);
    this.selectedPage=page;
    this.getPagePost(page);
  }
  getPagePost(page:number): void {
    this.postService.findAllPosts(page,this.size)
      .subscribe(page => {
        this.postPage = page;
      });
  }
}
