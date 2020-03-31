import { Category } from './category';
import { NGO } from './ngo';

export class Volunteering{
    constructor(
        public id:number,
        public name:string,
        public category:Category,
        public startDate:Date,
        public endDate:Date,
        public description:string,
        public image:string,
        public city:string,
        public ong:NGO,
        public email:string
    ){

    }
}