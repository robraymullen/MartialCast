import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { VideoService } from '../services/video.service';
import { VideoModel } from '../models/video.model';
import {Observable} from 'rxjs';
import {VerticalGalleryComponent} from '../vertical-gallery/vertical-gallery.component';
import {APIConfig} from "../config/api.url";
import { RequestType } from '../services/requesttype';

@Component({
  selector: 'app-video-player',
  templateUrl: './video-player.component.html',
  styleUrls: ['./video-player.component.css']
})
export class VideoPlayerComponent implements OnInit {
  videoURL : string = APIConfig.VIDEO_FILE;
  video$: Observable<VideoModel>;
  tagURL : string = APIConfig.TAGS.URL;
  collectionURL : string = APIConfig.COLLECTION.URL;

  constructor( private route: ActivatedRoute,
    private router: Router, private videoService : VideoService) { }

  ngOnInit() {
    this.video$ = this.route.paramMap.pipe(
      switchMap((params: ParamMap) =>
        this.videoService.runRequest(RequestType.VIDEO_SINGLE, params.get('id')))
    );
  }

}
