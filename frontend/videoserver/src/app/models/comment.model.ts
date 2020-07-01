export class CommentModel{

    id : number;
    content : string;
    postedAt : Date;

    constructor(id : number, content : string, postedAt : Date){
        this.id = id;
        this.content = content;
        this.postedAt = postedAt;
    }
}