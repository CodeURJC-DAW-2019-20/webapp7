import { Category } from './category';

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
        public ongId:number,
        public email:string
    ){

    }
}