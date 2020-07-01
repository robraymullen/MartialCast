import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VerticalGalleryComponent } from './vertical-gallery.component';

describe('VerticalGalleryComponent', () => {
  let component: VerticalGalleryComponent;
  let fixture: ComponentFixture<VerticalGalleryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VerticalGalleryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VerticalGalleryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
