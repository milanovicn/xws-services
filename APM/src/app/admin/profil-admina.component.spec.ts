import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfilAdminaComponent } from './profil-admina.component';

describe('ProfilAdminaComponent', () => {
  let component: ProfilAdminaComponent;
  let fixture: ComponentFixture<ProfilAdminaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProfilAdminaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfilAdminaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
