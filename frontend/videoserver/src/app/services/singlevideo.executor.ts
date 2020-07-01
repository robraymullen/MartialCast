import { Observable, throwError } from "rxjs";
import { Http, Response } from "@angular/http";
import { Executor } from "./executor";
import { VideoModel } from "../models/video.model";
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { map , catchError, retry } from "rxjs/operators";
import { BaseExecutor } from './base.executor';

export class SingleVideoExecutor extends BaseExecutor{

    execute(http : HttpClient) : Observable<VideoModel> {
        return http.get<VideoModel>(this.url).pipe(
            map(res => new VideoModel(res)),
            retry(3), // retry a failed request up to 3 times
            catchError(this.handleError)
        );
    }

}