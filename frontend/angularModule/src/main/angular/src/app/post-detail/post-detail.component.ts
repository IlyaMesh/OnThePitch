import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Post} from "../model/post";
import {PostDetailService} from "../service/post-detail.service";
import {Comment} from "../model/comment";
import {RatingServiceService} from "../service/rating-service.service";

@Component({
  selector: 'app-post-detail',
  templateUrl: './post-detail.component.html',
  styleUrls: ['./post-detail.component.css']
})
export class PostDetailComponent implements OnInit {
  post: Post;
  comments: Comment[];
  post_id: number;

  constructor(
    private actRoute: ActivatedRoute,
    private postDetService: PostDetailService,
    private ratingService: RatingServiceService
  ) {
  }

  ngOnInit(): void {
    this.post = new Post();
    this.post_id = this.actRoute.snapshot.params['post_id'];
    this.postDetService.findPost(this.post_id).subscribe(data => {
      this.post = data;
    })
    this.postDetService.findAllComments(this.post_id).subscribe(data => {
      this.comments = data;
    })
  }


  likeComment(comment_id: number) {
    this.ratingService.setLike(comment_id).subscribe(x=>console.log(x));
    //если лайкаем а уже стоит диз то убираем диз
    var comm: Comment;
    for (let comm of this.comments) {
      if (comm.comment_id == comment_id) {
        if(comm.disliked){
          comm.disliked =false;
          comm.dislikes--;
        }
        if (comm.liked) {
          comm.liked = false;
          comm.likes--;
        } else {
          comm.liked = true;
          comm.likes++;
        }
        return;
      }
      for (let comment of comm.comments) {
        if (comment.comment_id == comment_id) {
          if(comment.disliked){
            comment.disliked =false;
            comment.dislikes--;
          }
          if (comment.liked) {
            comment.liked = false;
            comment.likes--;
          } else {
            comment.liked = true;
            comment.likes++;
          }
          return;
        }
      }
    }
  }

  dislikeComment(comment_id: number) {
    this.ratingService.setDislike(comment_id).subscribe(x=>console.log(x));
    var comm: Comment;
    for (let comm of this.comments) {
      if (comm.comment_id == comment_id) {
        if(comm.liked){
          comm.liked =false;
          comm.likes--;
        }
        if (comm.disliked) {
          comm.disliked = false;
          comm.dislikes--;
        } else {
          comm.disliked = true;
          comm.dislikes++;
        }
        return;
      }
      for (let comment of comm.comments) {
        if (comment.comment_id == comment_id) {
          if(comment.liked){
            comment.liked =false;
            comment.likes--;
          }
          if (comment.disliked) {
            comment.disliked = false;
            comment.dislikes--;
          } else {
            comment.disliked = true;
            comment.dislikes++;
          }
          return;
        }
      }
    }
  }
}
