import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AboutusComponent } from './about-us.component';

describe('AboutUsComponent', () => {
  let component: AboutusComponent;
  let fixture: ComponentFixture<AboutusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AboutusComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AboutusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have the correct title', () => {
    expect(component.title).toBe('About Us');
  });

  it('should have 4 sections', () => {
    expect(component.sections.length).toBe(4);
  });

  it('should have a section with heading "MISSION ON" and content', () => {
    const missionSection = component.sections.find(section => section.heading === 'MISSION ON');
    expect(missionSection).toBeTruthy();
    expect(missionSection?.content.length).toBeGreaterThan(0);
    expect(missionSection?.content[0]).toBe('To Empower communities through innovative green solutions for a sustainable,harmonious planet.');
  });

  it('should have a section with heading "VISION ON" and content', () => {
    const visionSection = component.sections.find(section => section.heading === 'VISION ON');
    expect(visionSection).toBeTruthy();
    expect(visionSection?.content.length).toBeGreaterThan(0);
    expect(visionSection?.content[0]).toBe('To Inspire Global action for environmental conservation and thriving green future.');
  });

  it('should have a section with heading "CORE VALUES" and content', () => {
    const coreValuesSection = component.sections.find(section => section.heading === 'CORE VALUES');
    expect(coreValuesSection).toBeTruthy();
    expect(coreValuesSection?.content.length).toBeGreaterThan(0);
    expect(coreValuesSection?.content[0]).toBe('Sustainability, innovation, community empowerment,environmental stewardship, and collective action.');
  });

  it('should have a section with heading "ACHIEVEMENTS" and content', () => {
    const achievementsSection = component.sections.find(section => section.heading === 'ACHIEVEMENTS');
    expect(achievementsSection).toBeTruthy();  // Ensure section exists
    expect(achievementsSection?.content.length).toBeGreaterThan(0);  // Ensure it has content
    expect(achievementsSection?.content[0]).toBe('Implemented impactful green initiatives, advanced eco-technologies, and fostered global partnerships for a sustainable future.');
  });

});
