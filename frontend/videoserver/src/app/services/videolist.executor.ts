import { Actions } from "./requesttype";
import { Observable, throwError } from "rxjs";
import { Response } from "@angular/http";
import { Executor } from "./executor";
import { map , catchError, retry } from "rxjs/operators";
import { VideoModel } from "../models/video.model";
import { HttpClient,HttpErrorResponse } from '@angular/common/http';
import { BaseExecutor } from './base.executor';

export class VideoListExecutor extends BaseExecutor {

    execute(http : HttpClient) : Observable<VideoModel[]> {
      return http.get<any>(this.url).pipe(
        
        map((res : any) => {
            return res.map(video => {
                return new VideoModel(video)
            })
        }),
        retry(3), // retry a failed request up to 3 times
        catchError(super.handleError)
      );
    }

}