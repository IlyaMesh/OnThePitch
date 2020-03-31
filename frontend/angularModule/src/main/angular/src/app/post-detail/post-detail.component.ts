import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Post} from "../model/post";
import {PostDetailService} from "../service/post-detail.service";
import {Comment} from "../model/comment";

@Component({
  selector: 'app-post-detail',
  templateUrl: './post-detail.component.html',
  styleUrls: ['./post-detail.component.css']
})
export class PostDetailComponent implements OnInit {
  post:Post;
  comments : Comment[];
  post_id:number;
  constructor(
    private actRoute: ActivatedRoute,
    private postDetService: PostDetailService
  ) {}

  ngOnInit(): void {
    this.post = new Post();
    this.post_id = this.actRoute.snapshot.params['post_id'];
    this.postDetService.findPost(this.post_id).subscribe(data => {
      this.post = data;
    })
    this.postDetService.findAllComments(this.post_id).subscribe(data=>{this.comments = data;})
  }

}
