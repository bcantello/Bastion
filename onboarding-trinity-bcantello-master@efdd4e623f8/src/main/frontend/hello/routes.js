export const helloRoutes = function($stateProvider) {
  const helloState = {
    name: 'hello',
    url: 'hello',
    component: 'pjHello',
    resolve: {
      name: () => 'Angular'
    }
  };

  $stateProvider.state(helloState);
};
