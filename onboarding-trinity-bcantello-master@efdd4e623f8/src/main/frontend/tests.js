/* global require */

const requireSpecs = require.context('./', true, /\.spec.js$/);
requireSpecs.keys().forEach(requireSpecs);
