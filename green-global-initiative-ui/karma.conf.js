module.exports = function (config) {
    config.set({
      reporters: ['progress', 'kjhtml', 'coverage'],  // ✅ Add 'coverage' here
      coverageReporter: {
        type: 'html',  // ✅ Generates an HTML report
        dir: 'coverage/',  // ✅ Output directory
        subdir: '.', // ✅ Store coverage report in `coverage/`
        check: {
          global: {
            statements: 80,  // Adjust thresholds as needed
            branches: 80,
            functions: 80,
            lines: 80
          }
        }
      }
    });
  };