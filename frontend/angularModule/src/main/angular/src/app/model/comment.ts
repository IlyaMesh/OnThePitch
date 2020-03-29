export class Comment {
  comment_id : number;
  username : string;
  comments:{
    comment_id : number;
    username : string;
    text:string;
    likes:number;
    dislikes:number;
    reports:number;
  }[]
  text:string;
  likes:number;
  dislikes:number;
  reports:number;
}
