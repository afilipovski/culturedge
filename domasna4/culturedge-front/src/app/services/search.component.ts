import { IHeritageSite } from "../IHeritageSite";
import { Injectable } from "@angular/core";

/**
 * Strategy design pattern for search filtering through the cultural heritage sites.
 */


/**
 * Strategy Interface
 */
export interface FilterStrategy {
    filter(sites: IHeritageSite[], query: string): IHeritageSite[];
}


@Injectable({
    providedIn: 'root',
})

export class DefaultFilter implements FilterStrategy {
    filter(sites: IHeritageSite[], query: string): IHeritageSite[] {
        return sites;
    }

}


@Injectable({
    providedIn: 'root',
})
/**
 * Filtering based on name
 */
export class NameFilter implements FilterStrategy {
    filter(sites: IHeritageSite[], query: string): IHeritageSite[] {
        return sites.filter(a => a.name.toLowerCase().includes(query.toLowerCase()));
    }
}


@Injectable({
    providedIn: 'root',
})
/**
 * Filtering based on city
 */
export class CityFilter implements FilterStrategy{
    filter(sites: IHeritageSite[], query: string): IHeritageSite[] {
        return sites.filter(a => !a.city || a.city.toLowerCase().includes(query.toLowerCase()));
    }
}


@Injectable({
    providedIn: 'root',
})
/**
 * Filtering based on type
 */
export class TypeFilter implements FilterStrategy {
    filter(sites: IHeritageSite[], query: string): IHeritageSite[] {
        return sites.filter(a => {
            let tokens: string[] = [];
            if (a.historic != null) tokens.push(a.historic);
            if (a.tourism != null) tokens.push(a.tourism);
            return tokens.findIndex(token => token.toLowerCase().includes(query.toLowerCase())) !== -1;
        });
    }
}


@Injectable({
    providedIn: 'root',
})

export class FilterContext {
    private strategy: FilterStrategy = new DefaultFilter();

    setFilterStrategy(strategy: FilterStrategy): void {
        this.strategy = strategy;
    }

    applyFilter(sites: IHeritageSite[], query: string): IHeritageSite[] {
        return this.strategy.filter(sites, query);
    }
}