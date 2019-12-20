import {hello} from './module';
import 'angular-mocks';

describe('routes', function () {
  beforeEach(angular.mock.module(hello));

  afterEach(function () {
    this.element.remove()
  });

  beforeEach(inject([
    '$compile',
    '$rootScope',
    '$state',
    function ($compile, $rootScope, $state) {
      const scope = $rootScope.$new();
      this.element = $compile("<div ng-app='onboarding'><ui-view></ui-view></div>")(scope);
      this.go = (route)=>{
        $state.go(route);
        scope.$apply();
      }
    }
  ]));

  it('should route to an element that says hello when the user visits "hello"', function () {
    this.go('hello');
    expect(this.element[0].innerText).toEqual('Hello Angular');
  });

});
