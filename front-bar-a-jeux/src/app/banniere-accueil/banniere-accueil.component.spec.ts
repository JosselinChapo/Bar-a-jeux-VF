import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BanniereAccueilComponent } from './banniere-accueil.component';

describe('BanniereAccueilComponent', () => {
  let component: BanniereAccueilComponent;
  let fixture: ComponentFixture<BanniereAccueilComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BanniereAccueilComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BanniereAccueilComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
