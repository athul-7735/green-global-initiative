import { provideHttpClient } from '@angular/common/http';
import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ContactUsComponent } from './contact-us/contactus.component';
import { AboutusComponent } from './about-us/about-us.component';
import { authGuard } from './shared/guards/auth.guard';
import { roleGuard } from './shared/guards/role.guard';

export const routes: Routes = [
    { path: '', redirectTo: 'home', pathMatch: 'full' },
    { path: 'home', component: HomeComponent, providers: [provideHttpClient()] },
    { path: 'login', 
        loadChildren: () =>
        import('./authentication/authentication.module').then((m) => m.AuthenticationModule),
    },
    { path: 'grants', 
        loadChildren: () =>
        import('./grants/grants.module').then((m) => m.GrantsModule),
    },
    { path: 'about-us', component: AboutusComponent },
    { path: 'contact-us', component: ContactUsComponent },

    { path: 'admin', 
        loadChildren: () =>
        import('./admin/admin.module').then((m) => m.AdminModule),
        canActivate: [authGuard, roleGuard],
        data: { roles: ['Admin'] } 
    },
    { path: '**', redirectTo: 'home' },
];
