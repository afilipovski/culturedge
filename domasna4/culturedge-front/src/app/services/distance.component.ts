import { Injectable } from '@angular/core';
/**
 * Strategy design pattern for finding distance from the user to the cultural heritage sites.
 */

/**
 * Strategy Interface
 */
export interface DistanceCalculator {
    calculateDistance(lat1: number, lon1: number, lat2: number, lon2: number): number;
}

@Injectable({
    providedIn: 'root',
})


/**
 * Haversine Distance Calculation Strategy
 */
export class HaversineDistanceService implements DistanceCalculator {
    calculateDistance(lat1: number, lon1: number, lat2: number, lon2: number): number {
        const R = 6371;
        const dLat = this.degreesToRadians(lat2 - lat1);
        const dLon = this.degreesToRadians(lon2 - lon1);

        const a =
            Math.sin(dLat / 2) * Math.sin(dLat / 2) +
            Math.cos(this.degreesToRadians(lat1)) *
            Math.cos(this.degreesToRadians(lat2)) *
            Math.sin(dLon / 2) *
            Math.sin(dLon / 2);

        const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }

    private degreesToRadians(degrees: number): number {
        return degrees * (Math.PI / 180);
    }
}


// DistanceService as Context
@Injectable({
    providedIn: 'root',
})

export class DistanceService {
    private strategy: DistanceCalculator = new HaversineDistanceService();

    setStrategy(strategy: DistanceCalculator): void {
        this.strategy = strategy;
    }

    calculateDistance(lat1: number, lon1: number, lat2: number, lon2: number): number {
        return this.strategy.calculateDistance(lat1, lon1, lat2, lon2);
    }
}
