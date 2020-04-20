import { Component, OnInit } from '@angular/core';
import { CommentService } from '../../services/comment.service';
import { Comment } from '../../models/comment';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css'],
  providers: [CommentService]
})
export class ContactComponent implements OnInit {

  public comment: Comment;
  public status: String;

  constructor(
    private _commentService: CommentService,
    private _titleService: Title
  ) {
    this._titleService.setTitle("Contacto - VoluntaWeb");
    this.comment = new Comment(null, "", "", "");
  }

  ngOnInit() {
  }


  onSubmit(form) {
    this._commentService.create(this.comment).subscribe(
      response => {
        if (response.id != null) {
          this.status = "success";
          form.reset();
        } else {
          this.status = "error";
        }
      },
      error => {
        console.log(<any>error);
        this.status = "error";
      }
    );
  }

}
