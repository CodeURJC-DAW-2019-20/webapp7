export class Volunteering{
    constructor(
        public id:number,
        public name:string,
        public categoryId:number,
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