import { Category } from './category';
import { NGO } from './ngo';
import { UserVolunteering } from './uservolunteering';
import { Like } from './like';

export class Volunteering{
    constructor(
        public id:number,
        public joined_user: Set<UserVolunteering>,
        public likes: Set<Like>,
        public name:string,
        public category:Category,
        public startdate:Date,
        public enddate:Date,
        public description:string,
        public image:string,
        public city:string,
        public ong:NGO,
        public email:string
    ){

    }
}