import {Component, OnInit} from '@angular/core';
import {UserService} from "../service/user.service";
import {User} from "../model/user";

@Component({
  selector: 'app-admin-board',
  templateUrl: './admin-board.component.html',
  styleUrls: ['./admin-board.component.css']
})
export class AdminBoardComponent implements OnInit {

  users: User[];
  errorMessage = '';
  isError = false;

  constructor(private userService: UserService) {
  }

  ngOnInit(): void {
    this.userService.getUserList().subscribe(data => {
      this.users = data;
    });
  }

  promote(user_id: number) {
    for (let user of this.users) {
      if (user.user_id == user_id) {
        //user finded
        if (!this.findRole(user, 'ADMIN')) {
          //если в ролях не админ вызваем метод с клиента!
          this.userService.promote(user_id).subscribe(
            x => {this.reloadPage()},
            error => {
              this.errorMessage = error.error.message;
              this.isError = true;
            }
          );
         // this.reloadPage();
        }

      }
    }
  }

  findRole(user: User, roleName: string): boolean {
    for (let role of user.role) {
      if (role == roleName)
        return true;
    }
    return false;
  }

  demote(user_id: number) {
    for (let user of this.users) {
      if (user.user_id == user_id) {
        //user finded
        if (this.findRole(user, 'MODERATOR')) {
          //если в ролях модератор есть(а значит что и админ)  вызваем метод с клиента!
          this.userService.demote(user_id).subscribe(x => this.reloadPage());
          this.reloadPage();
        }

      }
    }
  }

  ban(user_id: number) {
    this.userService.ban(user_id).subscribe(x => this.reloadPage());
    this.reloadPage();
  }

  reloadPage() {
    window.location.reload();
  }
}
