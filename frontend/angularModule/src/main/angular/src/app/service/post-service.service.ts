import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Post } from '../model/post';
import {Observable} from "rxjs";
import {Comment} from "../model/comment";
import {PagePost} from "../model/page-post";

const API_URL_POSTS = 'http://localhost:8080/posts';
const API_URL_COMMENTS = 'http://localhost:8080/comments';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class PostServiceService {



  constructor(private http: HttpClient) {
  }
  public findAllPosts(page:number, size:number): Observable<PagePost> {
    return this.http.get<PagePost>(API_URL_POSTS+'?page='+page+'&size='+size,httpOptions);
  }
  public savePost(post: Post) {
    return this.http.post<Post>(API_URL_POSTS, post);
  }
}
