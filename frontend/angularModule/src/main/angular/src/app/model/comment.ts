export class Comment {
  comment_id: number;
  username: string;
  user_logo:string;
  comments: {
    comment_id: number;
    username: string;
    user_logo:string;
    text: string;
    likes: number;
    dislikes: number;
    isLiked: boolean;
    isDisliked: boolean;
    reports: number;
    reply_id: bigint;
    reply_name: string;
  }[]
  text: string;
  likes: number;
  dislikes: number;
  isLiked: boolean;
  isDisliked: boolean;
  reply_id:number;
  post_id:number;
}
