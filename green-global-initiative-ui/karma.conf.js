module.exports = function (config) {
  config.set({
    basePath: '',
    frameworks: ['jasmine', '@angular-devkit/build-angular'],
    plugins: [
      require('karma-jasmine'),
      require('karma-chrome-launcher'),
      require('karma-jasmine-html-reporter'),
      require('karma-coverage'),
      require('karma-junit-reporter'), // Add this line
      require('@angular-devkit/build-angular/plugins/karma')
    ],
    client: {
      clearContext: false // leave Jasmine Spec Runner output visible in browser
    },
    browsers: ['ChromeHeadlessCI'],
    customLaunchers: {
      ChromeHeadlessCI: {
        base: 'ChromeHeadless',
        flags: [
          '--no-sandbox',
          '--disable-gpu',
          '--remote-debugging-port=9222',
        ],
      },
    },
    reporters: ['progress', 'kjhtml', 'coverage', 'junit'],

    junitReporter: {
      outputDir: 'test-results',
      outputFile: 'results.xml',
      suite: '',
      useBrowserName: true, 
    },
    coverageReporter: {
      type: 'lcov', 
      dir: require('path').join(__dirname, './coverage'),
      subdir: '.',
      reporters: [
        { type: 'html' },
        { type: 'text-summary' },
        { type: 'lcovonly' }
      ],
      check: {
        global: {
          statements: 60,
          branches: 39,
          functions: 55,
          lines: 57,
        },
      },
    },
    files: [
      { pattern: 'src/assets/**/*', watched: false, included: false, served: true, nocache: false },
    ],
    proxies: {
      '/assets/': '/base/src/assets/',
    },
    singleRun: true, // Run tests once and exit (required for CI/CD)
    restartOnFileChange: false,
  });
};