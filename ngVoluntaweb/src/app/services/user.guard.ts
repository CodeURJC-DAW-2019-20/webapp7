import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { EntityService } from './entity.service';

@Injectable()
export class UserGuard implements CanActivate {
    constructor(
        private _router: Router,
        private _entityService: EntityService
    ){

    }

    canActivate(){
        let identity = this._entityService.getIdentity();
        let entity_type = this._entityService.getEntityType();

        if(identity && identity.name && entity_type == "user"){
            return true;
        } else {
            this._router.navigate(['/']);
            return false;
        }
    }
}