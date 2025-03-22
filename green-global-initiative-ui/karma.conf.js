module.exports = function (config) {
  config.set({
    // Specify the browsers to use (ChromeHeadless for headless testing)
    browsers: ['ChromeHeadless'], // ✅ Add this line

    // Configure custom launchers for ChromeHeadless
    customLaunchers: { // ✅ Add this block
      ChromeHeadless: {
        base: 'Chrome',
        flags: [
          '--no-sandbox', // Required for running in CI environments
          '--disable-gpu', // Disable GPU acceleration (not needed for headless)
          '--remote-debugging-port=9222', // Enable remote debugging
          '--headless', // Run in headless mode
        ],
      },
    },

    // Reporters configuration
    reporters: ['progress', 'kjhtml', 'coverage'], // ✅ Add 'coverage' here

    // Coverage reporter configuration
    coverageReporter: {
      type: 'html', // ✅ Generates an HTML report
      dir: 'coverage/', // ✅ Output directory
      subdir: '.', // ✅ Store coverage report in `coverage/`
      check: {
        global: {
          statements: 80, // Adjust thresholds as needed
          branches: 80,
          functions: 80,
          lines: 80,
        },
      },
    },

    // Other configurations (if any)
    // ...
  });
};