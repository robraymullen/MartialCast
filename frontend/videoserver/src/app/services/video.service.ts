import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import { RequestType } from './requesttype';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class VideoService {

	constructor(private http: HttpClient) { 

    }

  runRequest(requestType : RequestType, query? : string) : Observable<any> {
    if(query) {
      requestType.appendQueryToURL(query);
    }
    return requestType.execute(this.http);
  }
}
