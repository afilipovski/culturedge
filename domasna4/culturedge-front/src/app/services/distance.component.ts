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
    // Implementation of the Haversine formula to calculate distance between two coordinates
    calculateDistance(lat1: number, lon1: number, lat2: number, lon2: number): number {
        const R = 6371;
        const dLat = this.degreesToRadians(lat2 - lat1); // Latitude difference in radians
        const dLon = this.degreesToRadians(lon2 - lon1); // Longitude difference in radians

        const a =
            Math.sin(dLat / 2) * Math.sin(dLat / 2) +
            Math.cos(this.degreesToRadians(lat1)) *
            Math.cos(this.degreesToRadians(lat2)) *
            Math.sin(dLon / 2) *
            Math.sin(dLon / 2);

        const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c; // Distance in kilometers
    }

    // Helper function to convert degrees to radians
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

    // Setter method to dynamically change the strategy
    setStrategy(strategy: DistanceCalculator): void {
        this.strategy = strategy;
    }

    // Method to calculate distance using the selected strategy
    calculateDistance(lat1: number, lon1: number, lat2: number, lon2: number): number {
        return this.strategy.calculateDistance(lat1, lon1, lat2, lon2);
    }
}
