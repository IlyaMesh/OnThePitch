import {Component, OnInit} from '@angular/core';
import {Post} from "../model/post";
import {PostServiceService} from "../service/post-service.service";
import {TokenStorageService} from "../service/token-storage.service";
import {Comment} from "../model/comment";
import {PagePost} from "../model/page-post";
import {Router} from "@angular/router";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Filter} from "../model/filter";

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.css']
})
export class PostListComponent implements OnInit {

  posts: Post[];
  regForm:FormGroup;
  private firstName:FormControl;
  filterText:Filter;
  Name:string;
  private roles: string[];
  postPage: PagePost;
  selectedPage: number = 0;
  size: number = 5;
  page: number = 1;
  isLoggedIn = false;
  showModeratorBoard = false;
  isError = false;
  errorMessage = '';
  ifSearchPressed = false;

  constructor(private formBuilder:FormBuilder,private router: Router, private postService: PostServiceService, private tokenStorageService: TokenStorageService) {
    this.firstName=new FormControl('',[Validators.required])
    this.regForm=formBuilder.group({
      Name:this.Name
    })
  }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    console.log(this.isLoggedIn);
    if (this.isLoggedIn) {
      this.roles = this.tokenStorageService.getUser().roles;
      console.log(this.roles);
      this.showModeratorBoard = this.roles.includes('ADMIN') || this.roles.includes('MODERATOR');
      console.log(this.showModeratorBoard);
    }
    this.getPagePost(0);

  }

  onSelect(page: number): void {
    console.log("selected page : " + page);
    this.selectedPage = page;
    if(!this.ifSearchPressed){
    this.getPagePost(page);
    }
    else{
      this.getPagePost(page,this.filterText)
    }
  }

  getPagePost(page: number, filterText?:Filter): void {
    if(!filterText){
    this.postService.findAllPosts(page, this.size)
      .subscribe(page => {
        this.postPage = page;
      });
    }
    else {
        this.postService.findFilteredPosts(page,this.size,filterText).subscribe(page=>{this.postPage = page;});
    }
  }

  delete(post_id: number) {
    if (confirm("Are you sure to delete this post")) {
      this.postService.delete(post_id).subscribe(
        result => {
          console.log(result);
        },
        error => {
          this.errorMessage = error.error.message;
          console.log(this.errorMessage);
          this.isError = true;
        }
      );
    }
  }

  reloadPage() {
    window.location.reload();
  }

  onSubmit() {
    this.ifSearchPressed = true;
    this.selectedPage = 0;
    this.filterText = new Filter();
    console.log(this.regForm.get('Name').value);
    this.filterText.text = this.regForm.get('Name').value;
    this.getPagePost(0,this.filterText)
  }

  removeFilter() {
    this.ifSearchPressed = false;
    this.reloadPage();
  }
}
