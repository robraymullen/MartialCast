import { Component, OnInit } from '@angular/core';
import { VideoService } from '../services/video.service';
import { VideoProperty } from '../models/video-property.model';
import { APIConfig } from '../config/api.url';
import { Router } from '@angular/router';
import { RequestType } from '../services/requesttype';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  public styleCollapse : boolean = false;
  styles : VideoProperty[];
  instructors : VideoProperty[];
  collections : VideoProperty[];

  private instructorVideos : string = APIConfig.INSTRUCTORS.URL;
  private styleVideos : string = APIConfig.STYLES.URL;

  constructor(private videoService : VideoService, private router : Router) {
    this.router.routeReuseStrategy.shouldReuseRoute = function () {
      return false;
    };
   }

  ngOnInit() {
    this.videoService.runRequest(RequestType.STYLE_LIST).subscribe((res: VideoProperty[]) => 
      { this.styles = res;}
    );

    this.videoService.runRequest(RequestType.INSTRUCTOR_LIST).subscribe((res: VideoProperty[]) => 
      { this.instructors = res;}
    );

    this.videoService.runRequest(RequestType.COLLECTION_LIST).subscribe((res: VideoProperty[]) => 
    { this.collections = res;}
  );
  }

  hideStyle() : void {
    this.styleCollapse = true;
    console.log("hide style");
  }

  showStyle() : void {
    console.log("show style");
  }

}
