import { Observable } from "rxjs";
import { HttpClient } from '@angular/common/http';

export interface Executor {
    execute(http : HttpClient) : Observable<any>;

    appendQueryToURL(url : string) : Executor;
}