import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SifrarniciComponent } from './sifrarnici.component';

describe('SifrarniciComponent', () => {
  let component: SifrarniciComponent;
  let fixture: ComponentFixture<SifrarniciComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SifrarniciComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SifrarniciComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
