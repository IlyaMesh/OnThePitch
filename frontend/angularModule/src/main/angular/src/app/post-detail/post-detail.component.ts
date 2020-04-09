import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Post} from "../model/post";
import {PostDetailService} from "../service/post-detail.service";
import {Comment} from "../model/comment";
import {RatingServiceService} from "../service/rating-service.service";
import {CommentServiceService} from "../service/comment-service.service";
import {TokenStorageService} from "../service/token-storage.service";

@Component({
  selector: 'app-post-detail',
  templateUrl: './post-detail.component.html',
  styleUrls: ['./post-detail.component.css']
})
export class PostDetailComponent implements OnInit {
  post: Post;
  comments: Comment[];
  private roles: string[];
  post_id: number;
  showModeratorBoard = false;
  isLoggedIn = false;

  constructor(
    private actRoute: ActivatedRoute,
    private postDetService: PostDetailService,
    private ratingService: RatingServiceService,
    private tokenStorageService:TokenStorageService
  ) {
  }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    if (this.isLoggedIn) {
      this.roles = this.tokenStorageService.getUser().roles;
      this.showModeratorBoard = this.roles.includes('ADMIN' || 'MODERATOR');
    }
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
    this.ratingService.setLike(comment_id).subscribe(x => console.log(x));
    //если лайкаем а уже стоит диз то убираем диз
    var comm: Comment;
    for (let comm of this.comments) {
      if (comm.comment_id == comment_id) {
        if (comm.disliked) {
          comm.disliked = false;
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
          if (comment.disliked) {
            comment.disliked = false;
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
    this.ratingService.setDislike(comment_id).subscribe(x => console.log(x));
    var comm: Comment;
    for (let comm of this.comments) {
      if (comm.comment_id == comment_id) {
        if (comm.liked) {
          comm.liked = false;
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
          if (comment.liked) {
            comment.liked = false;
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

  dislikePost(post_id: number) {
    this.ratingService.setDislike(post_id).subscribe(x => console.log(x));
    if (this.post.liked) {
      this.post.liked = false;
      this.post.likes--;
    }
    if (this.post.disliked) {
      this.post.disliked = false;
      this.post.dislikes--;
    } else {
      this.post.disliked = true;
      this.post.dislikes++;
    }
  }

  likePost(post_id: number) {
    this.ratingService.setLike(post_id).subscribe(x => console.log(x));
    if (this.post.disliked) {
      this.post.disliked = false;
      this.post.dislikes--;
    }
    if (this.post.liked) {
      this.post.liked = false;
      this.post.likes--;
    }
    else{
      this.post.liked = true;
      this.post.likes++;
    }
  }
  delete(comment_id:number) {
    this.postDetService.deleteComment(comment_id).subscribe(result => this.reloadPage());
  }

  reloadPage() {
    window.location.reload();
  }
}
