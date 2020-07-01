import { Component, OnInit, ElementRef, EventEmitter, Input } from '@angular/core';
import {Http, Response} from '@angular/http';
import {Observable} from 'rxjs';
import { VideoService } from '../services/video.service';
import { VideoModel } from '../models/video.model';

@Component({
  selector: 'app-horizontal-gallery',
  templateUrl: './horizontal-gallery.component.html',
  styleUrls: ['./horizontal-gallery.component.css']
})
export class HorizontalGalleryComponent implements OnInit {
  @Input() videos : VideoModel[];
  constructor(private videoService : VideoService) { }

  ngOnInit() {
  }

}
