import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
// import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { GrantsDetailsComponent } from './grants-details/grants-details.component';
import { GrantApplicationComponent } from './grant-application/grant-application.component';
import { authGuard } from '../shared/guards/auth.guard';

const routes: Routes = [
    { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
   // { path: 'admin-dashboard', component: AdminDashboardComponent },
    { path: 'grant-application',  component: GrantApplicationComponent,  canActivate: [authGuard], },
    { path: '**',  redirectTo: 'dashboard', pathMatch: 'full' },
    { path: 'dashboard',  component: GrantsDetailsComponent },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GrantsRoutingModule { }
