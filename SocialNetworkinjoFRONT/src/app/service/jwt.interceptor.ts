import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { ApiService } from './api.service';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

    constructor(private apiService: ApiService) { }

    intercept(req: HttpRequest<any>, next: HttpHandler):
        Observable<HttpEvent<any>> {
            if(localStorage.getItem("jwt")) {
                const authReq = req.clone({
                    headers: req.headers.set(
                        "Authorization", "Bearer " + localStorage.getItem("jwt")
                    )
                });
                req = authReq;
            }
        return next.handle(req);
    }
}




