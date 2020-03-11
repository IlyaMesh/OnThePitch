import { Component, OnInit } from '@angular/core';
import {Post} from "../model/post";
import {PostServiceService} from "../service/post-service.service";

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.css']
})
export class PostListComponent implements OnInit {

  posts: Post[];
  constructor(private postService:PostServiceService) { }

  ngOnInit(): void {
    this.postService.findAll().subscribe(data =>{this.posts = data;});
  }

}
