var webpackConfig = require('./webpack.config.js');

// We're using the production webpack configuration here.  The only difference is that
// We want tests to be able to create their own entry points.
// So we null out the entry field in the production configuration.
// Without this line, tests will fail.
webpackConfig.entry = null;

module.exports = function(config) {
  config.set({

    basePath: './src/main/frontend',

    files: [
      './tests.js'
      // { pattern: './**/*.spec.js', watched: false}
    ],

    autoWatch: true,

    frameworks: ['jasmine'],

    browsers: ['ChromeHeadless'],

    junitReporter: {
      outputFile: 'karma-test-results.xml'
    },

    webpack: webpackConfig,

    webpackMiddleware: {
      noInfo: true
    },

    preprocessors: {
      // './**/*.spec.js': ['webpack', 'sourcemap']
      './tests.js': [
        'webpack',
        'sourcemap'
      ]
    }
  });
};
