/**
 * Print Hello for new employees to demonstrate Angular
 *
 *    Usage: <pj-hello
 *              name="anything you want">
 *           </pj-hello>
 *
 * @author David Ron <dron@payjunction.com>
 */
export const pjHelloComponent = {
  bindings: {
    name: '@'
  },
  template: '<div>Hello {{$ctrl.name}}</div>'
};
