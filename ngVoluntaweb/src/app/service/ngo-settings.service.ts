import {Injectable} from '@angular/core';
import { NGO } from '../models/ngo';

@Injectable()
export class NgoSettingsService{

    public ngo: NGO;

    constructor(){
        this.ngo = new NGO(1,"PruebaName","PruebaResponsibleName","PruebaResponsibleSurname","PruebaDescription","PruebaAddress","PruebaEmail",
        "PruebaCodigoPostal","PruebaImagen",null,"PruebaTelefono","PruebaPassword");
    }

    getNGO():NGO{

        //Here we have to implement the call to the API Rest

        return this.ngo;
    }

    updateNgo(ngo:NGO):void{
        this.ngo = ngo;

        console.log(this.ngo);

        //Here we have to implement the update of the ngo with the API Rest


    }

}