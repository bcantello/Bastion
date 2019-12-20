/* global require module, __dirname */

var path = require('path');
const { CleanWebpackPlugin } = require('clean-webpack-plugin');
var HtmlWebpackPlugin = require('html-webpack-plugin');

var DIST_PATH = path.resolve(__dirname, 'out/dist');

module.exports = {
  entry: './src/main/frontend/app.js',
  output: {
    filename: 'bundle.[chunkhash].js',
    path: DIST_PATH
  },
  mode: 'development',
  watch: true,
  devtool: 'inline-source-map',
  devServer: {
    contentBase: DIST_PATH
  },
  module: {
    rules: [
      {
        test: /\.js$/,
        loader: 'babel-loader'
      },{
        test: /\.html$/,
        loader: 'html-loader'
      },{
        // Ensure this rule runs first
        enforce: 'pre',
        test: /\.js$/,
        exclude: /node_modules/,
        loader: 'eslint-loader',
        options: {
          failOnError: true
        }
      }
    ]
  },
  plugins: [
    new CleanWebpackPlugin(),
    new HtmlWebpackPlugin({
      template: path.resolve(__dirname, 'src/main/frontend/index.html')
    })
  ]
};
