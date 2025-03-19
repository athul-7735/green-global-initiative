import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../../authentication/services/auth.service';
import { inject } from '@angular/core';

export const roleGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);
  const requiredRoles = route.data?.['roles'] as string[];

  if(authService.getUser() !==null){
    const user = authService.getUser();
    if (JSON.parse(user?user:'')?.isAdmin) {
      return true;
    }
  }
  router.navigate(['/login']);
  return false;
};
