import { Component, OnInit } from '@angular/core';
import { Comment } from '../../models/comment';
import { CommentService} from '../../services/comment.service';

@Component({
  selector: 'app-admin-comments',
  templateUrl: './admin-comments.component.html',
  styleUrls: ['./admin-comments.component.css'],
  providers: [CommentService]
})
export class AdminCommentsComponent implements OnInit {

  public comments:Set<Comment>;
  private status:string;

  constructor(private _commentService:CommentService) { 

    this.comments = null;

    this.getComments();
  
  }

  ngOnInit() {

  }


  getComments():void{
    this._commentService.get().subscribe(
      (response:any) =>{
        if(response){
          this.comments = response;
          this.status = "success";
        }
        else{
          this.status = 'error';
        }
      },
      error  =>{
        console.log(<any>error);
        this.status = 'error';
      }
    );
  }

  deleteComment(commentId:number){
    this._commentService.delete(commentId).subscribe(
      (response:Comment) =>{
        if(response){
          this.status = "success";

          this.getComments();

        }
      },
      error =>{
        console.log(<any>error);
        this.status = 'error';
      }
    );
  }

}
