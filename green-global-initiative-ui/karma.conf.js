module.exports = function (config) {
  config.set({
    // Specify the browsers to use (ChromeHeadless for headless testing)
    browsers: ['ChromeHeadlessCI'], // ✅ Use custom launcher name

    // Configure custom launchers for ChromeHeadless
    customLaunchers: { 
      ChromeHeadlessCI: { // ✅ Custom name to avoid conflicts
        base: 'ChromeHeadless', // ✅ Base should be 'ChromeHeadless' instead of 'Chrome'
        flags: [
          '--no-sandbox', // Required for running in CI environments
          '--disable-gpu', // Disable GPU acceleration (not needed for headless)
          '--remote-debugging-port=9222', // Enable remote debugging
        ],
      },
    },

    // Reporters configuration
    reporters: ['progress', 'kjhtml', 'coverage'],

    // Coverage reporter configuration
    coverageReporter: {
      type: 'html',
      dir: 'coverage/',
      subdir: '.',
      check: {
        global: {
          statements: 50,
          branches: 35,
          functions: 50,
          lines: 50,
        },
      },
    },

    // Other configurations (if any)
  });
};
