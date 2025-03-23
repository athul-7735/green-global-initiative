module.exports = function (config) {
  config.set({
    // ✅ Specify the browsers to use (ChromeHeadless for headless testing)
    browsers: ['ChromeHeadlessCI'],

    // ✅ Configure custom launchers for ChromeHeadless
    customLaunchers: { 
      ChromeHeadlessCI: {
        base: 'ChromeHeadless',
        flags: [
          '--no-sandbox', // Required for running in CI environments
          '--disable-gpu', 
          '--remote-debugging-port=9222',
        ],
      },
    },

    // ✅ Add JUnit and Coverage reporters
    reporters: ['progress', 'kjhtml', 'coverage', 'junit'],

    // ✅ JUnit reporter configuration (Test Results)
    junitReporter: {
      outputDir: 'test-results', // ✅ Store results in test-results/
      outputFile: 'results.xml',
      useBrowserName: false, // Prevents filename conflicts
    },

    // ✅ Coverage reporter configuration (Code Coverage)
    coverageReporter: {
      type: 'lcov', // ✅ Required for CI/CD
      dir: 'coverage/',
      subdir: '.',
    //  check: {
     //   global: {
      //    statements: 60,
      //    branches: 39,
      //    functions: 55,
      //    lines: 57,
       // },
      //},
    },
  });
};
