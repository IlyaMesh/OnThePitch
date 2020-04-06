import {MatchResult} from "./match-result";

export class PageMatch {
  content: MatchResult[];
  totalPages : number;
  totalElements : number;
  last : boolean;
  size : number ;
  first : boolean ;
  sort : string ;
  numberOfElements : number ;
}
