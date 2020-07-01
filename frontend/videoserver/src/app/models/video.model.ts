import {VideoProperty} from './video-property.model';
import {CommentModel} from './comment.model';

export class VideoModel{
    /*
    *Variable names have to exactly match the JSON object
    */
    id : number;
    name : string;
    description : string;
    postedAt : Date;
    instructors : VideoProperty[];
    forms : VideoProperty[];
    styles : VideoProperty[];
    techniques : VideoProperty[];
    tags : VideoProperty[];
    comments : CommentModel[];
    collection : VideoProperty;

    constructor(obj?: any){
        this.id = obj.id;
        this.name = obj.name;
        this.description = obj.description;
        this.postedAt = new Date(obj.postedAt);
        this.instructors = obj.instructors;
        this.forms = obj.forms;
        this.styles = obj.styles;
        this.techniques = obj.techniques;
        this.tags = obj.tags;
        this.comments = obj.comments;
        this.collection = obj.colletion;
    }
/*
    toString() : string {
        return "id["+this.id+"] name["+this.name+"] fileName["+this.fileName+"] previewFile"+this.previewFile+""
    }*/
}