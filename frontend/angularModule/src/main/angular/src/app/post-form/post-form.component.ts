import { Component} from '@angular/core';
import {Post} from "../model/post";
import {PostServiceService} from "../service/post-service.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-post-form',
  templateUrl: './post-form.component.html',
  styleUrls: ['./post-form.component.css']
})
export class PostFormComponent{
  post: Post;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private postService: PostServiceService) {
    this.post = new Post();
  }
  onSubmit() {
    this.postService.savePost(this.post).subscribe(result => this.gotoPostList());
  }

  gotoPostList() {
    this.router.navigate(['/posts']);
  }

}
