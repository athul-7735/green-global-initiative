import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../../authentication/services/auth.service';

export const authGuard: CanActivateFn = (route, state) => {
  
  const authService = inject(AuthService);
  const router = inject(Router);
  if(authService.getUser() !==null){
    return true;
  }
  router.navigate(['/login']);
  return false;
};