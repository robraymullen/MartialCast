import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { VideoModel } from '../models/video.model';
import {APIConfig} from "../config/api.url";

@Component({
  selector: 'app-video-preview',
  templateUrl: './video-preview.component.html',
  styleUrls: ['./video-preview.component.css']
})
export class VideoPreviewComponent implements OnInit {
  @Input() isVertical : boolean;
  @Input() video : VideoModel;
  videoURL : string;
  @ViewChild('videoPlayer') videoplayer: any;

  constructor() { }

  ngOnInit() {
    this.videoURL = APIConfig.VIDEO_PREVIEW_FILE_ROOT +this.video.id;
  }

  mouseEnter() : void {
    this.videoplayer.nativeElement.play();
  }

  mouseLeave() : void {
    this.videoplayer.nativeElement.pause();
  }

}
