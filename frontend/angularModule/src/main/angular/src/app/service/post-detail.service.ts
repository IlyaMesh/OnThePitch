import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Post} from "../model/post";
import {Comment} from "../model/comment";

const API_URL_COMMENTS = 'http://localhost:8080/comments/';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })};

@Injectable({
  providedIn: 'root'
})
export class PostDetailService {
  private postUrl : string;

  constructor(private http: HttpClient) {
    this.postUrl = "http://localhost:8080/post/";
  }

  public findPost(id): Observable<Post> {
      return this.http.get<Post>(this.postUrl+id);

  }
  public findAllComments(id):Observable<Comment[]>{
    return this.http.get<Comment[]>(API_URL_COMMENTS+id,httpOptions);
  }
  public deleteComment(id:number){
    return this.http.delete(API_URL_COMMENTS+id,httpOptions)
  }
}
