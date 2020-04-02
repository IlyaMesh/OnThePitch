import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {PostServiceService} from "../service/post-service.service";
import {CommentServiceService} from "../service/comment-service.service";
import {Comment} from "../model/comment";

@Component({
  selector: 'app-comment-form',
  templateUrl: './comment-form.component.html',
  styleUrls: ['./comment-form.component.css']
})
export class CommentFormComponent implements OnInit {

  comment:Comment;
  post_id:number;
  reply_to:number;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private commentService: CommentServiceService) {
    this.comment = new Comment();
  }

  onSubmit(): void {
    this.commentService.saveComment(this.comment,this.post_id).subscribe(result => this.gotoPostList());
  }

  gotoPostList() {
    this.router.navigate(['/post-detail/'+this.post_id]);
  }

  ngOnInit(): void {
    this.post_id = this.route.snapshot.params['post_id'];
    this.reply_to = this.route.snapshot.params['reply_to'];
    this.comment.reply_id = this.reply_to;
    this.comment.post_id = this.post_id;
  }

}
