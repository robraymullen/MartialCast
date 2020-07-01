import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { HorizontalGalleryComponent } from './horizontal-gallery/horizontal-gallery.component';
import { VerticalGalleryComponent } from './vertical-gallery/vertical-gallery.component';
import { LargeGalleryComponent } from './large-gallery/large-gallery.component';
import { VideoWindowComponent } from './video-window/video-window.component';
import { VideoPreviewComponent } from './video-preview/video-preview.component';
import { HomeGalleryComponent } from './home-gallery/home-gallery.component';
import { VideoService } from './services/video.service';
import { HeaderComponent } from './header/header.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { VideoPlayerComponent } from './video-player/video-player.component';
import { RequestType } from './services/requesttype';

const appRoutes: Routes = [
  { path: 'home', component: HomeGalleryComponent, data : {title: 'Recently Added Videos', state: 'HOME', requestType : RequestType.HOME}},
  { path: 'video/:id', component: VideoPlayerComponent, data : { requestType : RequestType.VIDEO_SINGLE} },
  { path: 'search/:query', component: HomeGalleryComponent, data : {title:'Search', state: 'SEARCH', requestType : RequestType.SEARCH}},
  { path: 'instructor/:query', component : HomeGalleryComponent, data : {title:'Videos by', state: 'INSTRUCTOR', requestType : RequestType.INSTRUCTOR_VIDEOS}},
  { path: 'style/:query', component : HomeGalleryComponent, data : {title:'', state: 'STYLE', requestType : RequestType.STYLE_VIDEOS}},
  { path: 'tag/:query', component : HomeGalleryComponent, data : {title:'', state: 'TAG', requestType : RequestType.TAG_VIDEOS}},
  { path: 'collection/:query', component: HomeGalleryComponent, data : {title: 'Collection', state:'COLLECTION', requestType : RequestType.COLLECTION_VIDEOS}},
  { path: '',
    redirectTo: '/home',
    pathMatch: 'full'
  }
];

@NgModule({
  declarations: [
    AppComponent,
    HorizontalGalleryComponent,
    VerticalGalleryComponent,
    LargeGalleryComponent,
    VideoWindowComponent,
    VideoPreviewComponent,
    HomeGalleryComponent,
    HeaderComponent,
    SidebarComponent,
    VideoPlayerComponent
  ],
  imports: [
    RouterModule.forRoot(
      appRoutes),
    BrowserModule,
    HttpClientModule,
  ],
  providers: [VideoService],
  bootstrap: [AppComponent]
})
export class AppModule { }
