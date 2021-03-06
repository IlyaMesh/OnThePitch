import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
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
  isError = false;
  errorMessage = '';

  constructor(
    private actRoute: ActivatedRoute,
    private postDetService: PostDetailService,
    private ratingService: RatingServiceService,
    private tokenStorageService: TokenStorageService,
    private router: Router,
  ) {
  }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    if (this.isLoggedIn) {
      this.roles = this.tokenStorageService.getUser().roles;
      this.showModeratorBoard = this.roles.includes('ADMIN') || this.roles.includes('MODERATOR');
    }
    this.post = new Post();
    this.post_id = this.actRoute.snapshot.params['post_id'];
    this.postDetService.findPost(this.post_id).subscribe(data => {
        this.post = data;
      },
      error => {
        this.errorMessage = error.error.message;
        this.isError = true;
      })
      this.postDetService.findAllComments(this.post_id).subscribe(data => {
        this.comments = data;
        console.log(data);
      })

  }


  likeComment(comment_id: number) {
    if(!this.isLoggedIn){
      this.gotoLoginForm();
    }
    this.ratingService.setLike(comment_id).subscribe(x => console.log(x));
    //если лайкаем а уже стоит диз то убираем диз
    var comm: Comment;
    for (let comm of this.comments) {
      if (comm.comment_id == comment_id) {
        if (comm.isDisliked) {
          comm.isDisliked = false;
          comm.dislikes--;
        }
        if (comm.isLiked) {
          comm.isLiked = false;
          comm.likes--;
        } else {
          comm.isLiked = true;
          comm.likes++;
        }
        return;
      }
      for (let comment of comm.comments) {
        if (comment.comment_id == comment_id) {
          if (comment.isDisliked) {
            comment.isDisliked = false;
            comment.dislikes--;
          }
          if (comment.isLiked) {
            comment.isLiked = false;
            comment.likes--;
          } else {
            comment.isLiked = true;
            comment.likes++;
          }
          return;
        }
      }
    }
  }

  dislikeComment(comment_id: number) {
    if(!this.isLoggedIn){
      this.gotoLoginForm();
    }
    this.ratingService.setDislike(comment_id).subscribe(x => console.log(x));
    var comm: Comment;
    for (let comm of this.comments) {
      if (comm.comment_id == comment_id) {
        if (comm.isLiked) {
          comm.isLiked = false;
          comm.likes--;
        }
        if (comm.isDisliked) {
          comm.isDisliked = false;
          comm.dislikes--;
        } else {
          comm.isDisliked = true;
          comm.dislikes++;
        }
        return;
      }
      for (let comment of comm.comments) {
        if (comment.comment_id == comment_id) {
          if (comment.isLiked) {
            comment.isLiked = false;
            comment.likes--;
          }
          if (comment.isDisliked) {
            comment.isDisliked = false;
            comment.dislikes--;
          } else {
            comment.isDisliked = true;
            comment.dislikes++;
          }
          return;
        }
      }
    }
  }

  dislikePost(post_id: number) {
    if(!this.isLoggedIn){
      this.gotoLoginForm();
    }
    this.ratingService.setDislike(post_id).subscribe(x => console.log(x));
    if (this.post.isLiked) {
      this.post.isLiked = false;
      this.post.likes--;
    }
    if (this.post.isDisliked) {
      this.post.isDisliked = false;
      this.post.dislikes--;
    } else {
      this.post.isDisliked = true;
      this.post.dislikes++;
    }
  }

  likePost(post_id: number) {
    if(!this.isLoggedIn){
      this.gotoLoginForm();
    }
    this.ratingService.setLike(post_id).subscribe(x => console.log(x));
    if (this.post.isDisliked) {
      this.post.isDisliked = false;
      this.post.dislikes--;
    }
    if (this.post.isLiked) {
      this.post.isLiked = false;
      this.post.likes--;
    } else {
      this.post.isLiked = true;
      this.post.likes++;
    }
  }

  delete(comment_id: number) {
    if(confirm("Are you sure to delete this comment")) {
    this.postDetService.deleteComment(comment_id).subscribe(result => this.reloadPage());
    }
  }

  reloadPage() {
    window.location.reload();
  }

  gotoLoginForm() {
    this.router.navigate(['/login']);
  }
}
