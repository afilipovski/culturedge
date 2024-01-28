// Interface representing a heritage site.
export interface IHeritageSite {
    name: string;
    lat: number;
    lon: number;
    city: string;
    historic?: string | null;
    tourism?: string | null;
}