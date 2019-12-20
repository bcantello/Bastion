import {hello} from '../module';
import 'angular-mocks';

describe('pjHello Component', function () {
  beforeEach(angular.mock.module(hello));

  /**
   * Removing the element is important because leaving them behind can cause memory leaks and
   * conflicts between elements left dangling between tests in this file.
   *
   * If an element is listening for clicks or some other sort of handler, these can
   * cause additional conflicts betweeen tests in this file if they aren't cleaned up.
   */
  afterEach(function () {
    this.element.remove()
  });

  beforeEach(inject([
    '$compile',
    '$rootScope',
    function ($compile, $rootScope) {
      /**
       * Why use this instead of creating variables:
       * https://gist.github.com/traviskaufman/11131303
       */
      const scope = $rootScope.$new();
      this.element = $compile("<pj-hello name='David'></pj-hello>")(scope);
      scope.$apply();
    }
  ]));

  it('should create an element that says hello', function () {
    expect(this.element[0].innerText).toEqual('Hello David');
  });
});
