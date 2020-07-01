import { Component, OnInit, Input } from '@angular/core';
import { VideoModel } from '../models/video.model';
import { VideoService } from '../services/video.service';
import { RequestType } from '../services/requesttype';

@Component({
  selector: 'app-vertical-gallery',
  templateUrl: './vertical-gallery.component.html',
  styleUrls: ['./vertical-gallery.component.css']
})
export class VerticalGalleryComponent implements OnInit {
  @Input() video : VideoModel;
  current: number = 0;
  items: Array<any> = new Array<any>();
  relatedVideosByInstructor : VideoModel[];
  relatedByStyle : VideoModel[];
  relatedByTechnique : VideoModel[];
  relatedByInstructors : VideoModel[];
  relatedByCollection : VideoModel[];
  
  constructor(private videoService : VideoService) {}

  ngOnInit(){
    
    if(this.video.styles !== null) {
      let styleJoin = this.video.styles.map(item => item.name).join();
      this.videoService.runRequest(RequestType.STYLE_VIDEOS, styleJoin).subscribe((res: VideoModel[]) => 
      { 
        this.relatedByStyle = res.filter((video) =>{
          return video.id !== this.video.id;
        });
        if(this.relatedByStyle.length !== 0) {
          this.items.push({
            "title":"Style",
            "videos":this.relatedByStyle
          });
        }
      }
      );
    }
    
    
    if(this.video.instructors !== null) {
      let instructorJoin = this.video.instructors.map(item=>item.name).join();
      this.videoService.runRequest(RequestType.INSTRUCTOR_VIDEOS, instructorJoin).subscribe((res: VideoModel[]) => 
      { 
        this.relatedByInstructors = res.filter((video) =>{
          return video.id !== this.video.id;
        });
        if(this.relatedByInstructors.length !== 0) {
          this.items.push({
            "title":"Instructors",
            "videos":this.relatedByInstructors
          });
        }
        
      }
      );
    }
    

    if(this.video.collection) {
      this.videoService.runRequest(RequestType.COLLECTION_VIDEOS, this.video.collection.name).subscribe((res: VideoModel[]) => 
      { 
        this.relatedByCollection = res.filter((video) =>{
          return video.id !== this.video.id;
        });
        if(this.relatedByCollection.length !== 0){
          this.items.push({
            "title":"Collection",
            "videos":this.relatedByCollection
          });
        }
        
      }
      );
    }
    
    if(this.video.techniques !== null) {
      let techniqueJoin = this.video.techniques.map(item=> item.name).join();
      this.videoService.runRequest(RequestType.TECHNIQUE_VIDEOS, techniqueJoin).subscribe((res: VideoModel[]) => 
      { 
        this.relatedByTechnique = res.filter((video) =>{
          return video.id !== this.video.id;
        });
        if(this.relatedByTechnique.length !== 0) {
          this.items.push({
            "title":"Techniques",
            "videos":this.relatedByTechnique
          });
        }
        
      }
      );
    }
    
  }

}
