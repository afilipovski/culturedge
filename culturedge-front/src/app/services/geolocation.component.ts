import { Injectable } from '@angular/core';
import { Subject, Observable } from 'rxjs';

@Injectable({
    providedIn: 'root',
})
export class LocationService {
    // Subject to emit location updates
    private locationSubject = new Subject<{ latitude: number; longitude: number } | null>();

    // Method to get an observable for location updates
    getLocationObservable(): Observable<{ latitude: number; longitude: number } | null> {
        return this.locationSubject.asObservable();
    }

    // Method to set user's location and emit the update
    setUserLocation(latitude: number, longitude: number): void {
        this.locationSubject.next({ latitude, longitude });
    }
}