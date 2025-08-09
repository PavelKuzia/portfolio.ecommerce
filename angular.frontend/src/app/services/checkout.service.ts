import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Country } from '../common/country';
import { State } from '../common/state';
import { map } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class CheckoutDataService {
  private baseUrl = `${environment.API_URL}/api/`;
  private baseCountriesUrl = `${environment.API_URL}/api/countries`;

  constructor(private HttpClient: HttpClient) {}

  public getAllCountries(): Observable<Country[]> {
    return this.HttpClient.get<GetResponseCountries>(
      this.baseCountriesUrl
    ).pipe(map((response) => response._embedded.countries));
  }

  public getAllStates(code: string): Observable<State[]> {
    let url = `${this.baseUrl}states/search/findByCountryCode?code=${code}`;
    return this.HttpClient.get<GetResponseStates>(url).pipe(
      map((response) => response._embedded.states)
    );
  }
}

interface GetResponseCountries {
  _embedded: {
    countries: Country[];
  };
}

interface GetResponseStates {
  _embedded: {
    states: State[];
  };
}
