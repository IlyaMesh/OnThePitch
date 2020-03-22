import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Post } from '../model/post';
import {Observable} from "rxjs";

const API_URL = 'http://localhost:8080/posts';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class PostServiceService {



  constructor(private http: HttpClient) {
  }
  public findAll(): Observable<Post[]> {
    return this.http.get<Post[]>(API_URL,httpOptions);
  }

  public save(post: Post) {
    return this.http.post<Post>(API_URL, post);
  }
}
