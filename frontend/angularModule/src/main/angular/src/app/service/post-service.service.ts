import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Post } from '../model/post';
import {Observable} from "rxjs";


@Injectable()
export class PostServiceService {

  private postUrl : string;

  constructor(private http: HttpClient) {
    this.postUrl = "http://localhost:8080/posts";
  }
  public findAll(): Observable<Post[]> {
    return this.http.get<Post[]>(this.postUrl);
  }

  public save(post: Post) {
    return this.http.post<Post>(this.postUrl, post);
  }
}
