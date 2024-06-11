// Import the necessary Angular module for dynamic browser platform.
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

// Import the root module of the Angular application.
import { AppModule } from './app/app.module';

// Import the necessary Angular module for dynamic browser platform.
platformBrowserDynamic().bootstrapModule(AppModule)
  .catch(err => console.error(err));
