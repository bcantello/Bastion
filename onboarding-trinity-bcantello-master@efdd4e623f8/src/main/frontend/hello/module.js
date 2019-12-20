import uiRouter from '@uirouter/angularjs';
import { helloRoutes } from './routes';
import { pjHelloComponent } from './components/pjHello';

const HELLO = 'onboarding.hello';
export const hello = angular.module(HELLO, [uiRouter])
    .config(helloRoutes)
    .component('pjHello', pjHelloComponent)
    .name;
