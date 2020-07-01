import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LargeGalleryComponent } from './large-gallery.component';

describe('LargeGalleryComponent', () => {
  let component: LargeGalleryComponent;
  let fixture: ComponentFixture<LargeGalleryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LargeGalleryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LargeGalleryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
