import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BandeauAccueilComponent } from './bandeau-accueil.component';

describe('BandeauAccueilComponent', () => {
  let component: BandeauAccueilComponent;
  let fixture: ComponentFixture<BandeauAccueilComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BandeauAccueilComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BandeauAccueilComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
