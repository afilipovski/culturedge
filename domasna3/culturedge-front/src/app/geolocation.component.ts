import { Injectable } from '@angular/core';
import { Subject, Observable } from 'rxjs';

@Injectable({
    providedIn: 'root',
})
export class LocationService {
    private locationSubject = new Subject<{ latitude: number; longitude: number } | null>();

    getLocationObservable(): Observable<{ latitude: number; longitude: number } | null> {
        return this.locationSubject.asObservable();
    }

    setUserLocation(latitude: number, longitude: number): void {
        this.locationSubject.next({ latitude, longitude });
    }
}