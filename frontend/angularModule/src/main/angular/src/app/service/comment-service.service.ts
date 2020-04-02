import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Post} from "../model/post";
import {Comment} from "../model/comment";

const API_URL_COMMENTS = 'http://localhost:8080/comments/';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})
export class CommentServiceService {

  constructor(private http: HttpClient) { }

  public saveComment(comment: Comment, id) {
    return this.http.post<Comment>(API_URL_COMMENTS+id, comment,httpOptions);
  }

}
