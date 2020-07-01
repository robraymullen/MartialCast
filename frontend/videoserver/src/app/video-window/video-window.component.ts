import { Component, OnInit, ElementRef, EventEmitter } from '@angular/core';
import { VideoModel } from '../models/video.model';

@Component({
  selector: 'app-video-window',
  templateUrl: './video-window.component.html',
  styleUrls: ['./video-window.component.css']
})
export class VideoWindowComponent implements OnInit {

  video : VideoModel;

  constructor() { }

  ngOnInit() : void{
  }

}
