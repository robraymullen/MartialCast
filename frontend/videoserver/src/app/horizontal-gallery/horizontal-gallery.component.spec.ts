import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HorizontalGalleryComponent } from './horizontal-gallery.component';

describe('HorizontalGalleryComponent', () => {
  let component: HorizontalGalleryComponent;
  let fixture: ComponentFixture<HorizontalGalleryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HorizontalGalleryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HorizontalGalleryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
