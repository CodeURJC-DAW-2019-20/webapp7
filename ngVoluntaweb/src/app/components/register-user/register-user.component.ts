import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { UserService } from '../../services/user.service';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css'],
  providers: [UserService]
})
export class RegisterUserComponent implements OnInit {

  public user: User;
  public status: String;

  constructor(
    private _userService: UserService,
    private _titleService: Title
  ) {
    this._titleService.setTitle("Registrarse como usuario - VoluntaWeb");
    this.user = new User(null, null, null, "", "", "", "", "", "", ['ROLE_USER'], "false", null);
  }

  ngOnInit() {
  }


  onSubmit(form) {
    this._userService.register(this.user).subscribe(response => {
      if (response.id != null) {
        this.status = "success";
      } else {
        this.status = "error";
      }
    },
      error => {
        console.log(<any>error);
        this.status = "error";
      })

  }

}
