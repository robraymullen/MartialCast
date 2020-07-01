import { Observable } from "rxjs";
import { Executor } from "./executor";
import { catchError, retry } from "rxjs/operators";
import { VideoProperty } from "../models/video-property.model";
import { HttpClient } from '@angular/common/http';
import { BaseExecutor } from './base.executor';

export class PropertyNameListExecutor extends BaseExecutor {

    execute(http : HttpClient) : Observable<VideoProperty[]> {
        return http.get<VideoProperty[]>(this.url).pipe(
            retry(3), // retry a failed request up to 3 times
            catchError(this.handleError)
        );
    }

}