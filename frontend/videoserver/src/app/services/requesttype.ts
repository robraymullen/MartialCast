import { APIConfig } from "../config/api.url";
import { VideoListExecutor } from "./videolist.executor";
import { Executor } from "./executor";
import { PropertyNameListExecutor } from "./propertyname.list.executor";
import { SingleVideoExecutor } from "./singlevideo.executor";
import { Observable } from "rxjs";
import { HomeListExecutor } from "./homelist.executor";
import { HttpClient } from '@angular/common/http';

/*enum RequestType {
    HOME,
    SEARCH,
    INSTRUCTOR_VIDEOS,
    STYLE_VIDEOS,
    TAG_VIDEOS,
    COLLECTION_VIDEOS,
    VIDEO_SINGLE,
    INSTRUCTOR_LIST,
    STYLE_LIST,
    TAG_LIST,
    COLLECTION_LIST,


    related
    list of videos
    single video
    list of existing names on a property
}*/

export enum Actions {
    VIDEO_LIST,
    RELATED,
    SINGLE_VIDEO,
    PROPERTY_NAMES
}

export class RequestType {

    static readonly HOME = new RequestType(new HomeListExecutor(APIConfig.HOME.URL));
    static readonly SEARCH = new RequestType(new VideoListExecutor(APIConfig.SEARCH.URL));
    static readonly INSTRUCTOR_VIDEOS = new RequestType(new VideoListExecutor(APIConfig.INSTRUCTORS.API.URL));
    static readonly STYLE_VIDEOS = new RequestType(new VideoListExecutor(APIConfig.STYLES.API.URL));
    static readonly TAG_VIDEOS = new RequestType(new VideoListExecutor(APIConfig.TAGS.API.URL));
    static readonly COLLECTION_VIDEOS = new RequestType(new VideoListExecutor(APIConfig.COLLECTION.API.URL));
    static readonly TECHNIQUE_VIDEOS = new RequestType(new VideoListExecutor(APIConfig.TECHNIQUES.API.URL));
    static readonly VIDEO_SINGLE = new RequestType(new SingleVideoExecutor(APIConfig.SINGLE_VIDEO.URL));
    static readonly INSTRUCTOR_LIST = new RequestType(new PropertyNameListExecutor(APIConfig.PROPERTY_NAME_LIST.INSTRUCTORS.URL));
    static readonly STYLE_LIST = new RequestType(new PropertyNameListExecutor(APIConfig.PROPERTY_NAME_LIST.STYLES.URL));
    static readonly TAG_LIST = new RequestType(new PropertyNameListExecutor(APIConfig.PROPERTY_NAME_LIST.TAGS.URL));
    static readonly COLLECTION_LIST = new RequestType(new PropertyNameListExecutor(APIConfig.PROPERTY_NAME_LIST.COLLECTIONS.URL));
    static readonly TECHNIQUE_LIST = new RequestType(new PropertyNameListExecutor(APIConfig.PROPERTY_NAME_LIST.TECHNIQUES.URL));

    private executor : Executor;

    private  baseURL : string;

    public constructor(executor : Executor) {
        this.executor = executor;
    }

    public execute(http : HttpClient) : Observable<any> {
        return this.executor.execute(http);
    }

    public appendQueryToURL(url : string) {
        this.executor.appendQueryToURL(url);
    }

} 


/**
 *  { path: 'home', component: HomeGalleryComponent, data : {title: 'Recently Added Videos', state: 'HOME'}},
  { path: 'video/:id', component: VideoPlayerComponent },
  { path: 'search/:query', component: HomeGalleryComponent, data : {title:'Search', state: 'SEARCH'}},
  { path: 'instructor/:query', component : HomeGalleryComponent, data : {title:'Videos by', state: 'INSTRUCTOR'}},
  { path: 'style/:query', component : HomeGalleryComponent, data : {title:'', state: 'STYLE'}},
  { path: 'tag/:query', component : HomeGalleryComponent, data : {title:'', state: 'TAG'}},
  { path: 'collection/:query', component: HomeGalleryComponent, data : {title: 'Collection', state:'COLLECTION'}},
 
 
 static readonly HOME = new RequestType(new VideoListExecutor(APIConfig.HOME.URL));
    static readonly SEARCH = new RequestType(new VideoListExecutor(APIConfig.SEARCH.URL));
    static readonly INSTRUCTOR_VIDEOS = new RequestType(new VideoListExecutor(APIConfig.INSTRUCTORS.URL));
    static readonly STYLE_VIDEOS = new RequestType(new VideoListExecutor(APIConfig.STYLES.URL));
    static readonly TAG_VIDEOS = new RequestType(new VideoListExecutor(APIConfig.TAGS.URL));
    static readonly COLLECTION_VIDEOS = new RequestType(new VideoListExecutor(APIConfig.COLLECTION.URL));
    static readonly VIDEO_SINGLE = new RequestType(new SingleVideoExecutor(APIConfig.SINGLE_VIDEO.URL));
    static readonly INSTRUCTOR_LIST = new RequestType(new PropertyNameListExecutor(APIConfig.PROPERTY_NAME_LIST.INSTRUCTORS.URL));
    static readonly STYLE_LIST = new RequestType(new PropertyNameListExecutor(APIConfig.PROPERTY_NAME_LIST.STYLES.URL));
    static readonly TAG_LIST = new RequestType(new PropertyNameListExecutor(APIConfig.PROPERTY_NAME_LIST.TAGS.URL));
    static readonly COLLECTION_LIST = new RequestType(new PropertyNameListExecutor(APIConfig.PROPERTY_NAME_LIST.COLLECTIONS.URL));
    static readonly TECHNIQUE_LIST = new RequestType(new PropertyNameListExecutor(APIConfig.PROPERTY_NAME_LIST.TECHNIQUES.URL));

 
 
 
 
 
 
 
  */