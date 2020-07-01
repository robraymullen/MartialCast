import { Component, OnInit, Input } from '@angular/core';
import { VideoModel } from '../models/video.model';
import { VideoService } from '../services/video.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { RequestType } from '../services/requesttype';

@Component({
  selector: 'app-home-gallery',
  templateUrl: './home-gallery.component.html',
  styleUrls: ['./home-gallery.component.css']
})
export class HomeGalleryComponent implements OnInit {
  videos : VideoModel[];
  title : string;
  state : string;
  query : string;
  requestType : RequestType;
  constructor(private videoService : VideoService, 
    private router : Router, private activatedRoute : ActivatedRoute) { }

  ngOnInit() {

    
    //console.log("paramMap:" +this.router.);
    this.activatedRoute.data.subscribe((res: any) => {
      this.title = res.title;
      this.state = res.state;
      this.requestType = res.requestType;
    });
    this.activatedRoute.params.subscribe( params => {
      this.query = params['query'];
    }); 

    this.videoService.runRequest(this.requestType, this.query).subscribe((res: VideoModel[]) => 
      { this.videos = res;}
     );

    /*if(this.state == "HOME") {
      this.videoService.runRequest(this.requestType, this.query).subscribe((res: VideoModel[]) => 
      { this.videos = res;}
     );
      /*this.videoService.getRecentlyAdded().subscribe((res: VideoModel[]) => 
        { this.videos = res;}
       );
    } else if (this.state == "SEARCH") {
      this.videoService.getSearchResults(this.query).subscribe((res: VideoModel[]) => 
        { 
          this.videos = res;}
       );
    } else if (this.state == "INSTRUCTOR") {
      this.videoService.getVideosByInstructor(this.query).subscribe((res: VideoModel[]) => 
      { 
        this.videos = res;
        this.title = this.title + " "+ this.query;
      }
     );
    } else if (this.state == "STYLE") {
        this.videoService.getVideosByStyle(this.query).subscribe((res: VideoModel[]) => 
        { 
          this.videos = res;
          this.title = this.query + " videos";
        }
      );
    } else if (this.state == "TAG") {
        this.videoService.getVideosByTag(this.query).subscribe((res: VideoModel[]) => 
        { 
          this.videos = res;
          this.title = "Videos with tag: "+this.query;
        }
      );
    } else if(this.state = "COLLECTION") {
      this.videoService.getVideosByCollection(this.query).subscribe((res: VideoModel[]) => 
      { 
        this.videos = res;
        this.title = this.query;
      }
    );
    }*/
    
    
  }

}
