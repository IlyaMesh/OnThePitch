import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Post } from '../model/post';
import {Observable} from "rxjs";
import {Comment} from "../model/comment";
import {PagePost} from "../model/page-post";
import {Filter} from "../model/filter";

const API_URL_POSTS = 'http://localhost:8080/posts';
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

  delete(post_id: number) {
    return this.http.delete(API_URL_POSTS+'/'+post_id);
  }

  findFilteredPosts(page: number, size: number, filterText: Filter) {
    return this.http.post<PagePost>(API_URL_POSTS+'/filtered'+'?page='+page+'&size='+size,filterText,httpOptions);
  }
}
