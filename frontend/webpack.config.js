
module.exports = {
  entry: {
    main: "./src/index.tsx",
  },
  output: {
    path: __dirname + "/../target/classes/static",
    filename: "[name].js",
  },
  resolve: {
    extensions: ["*", ".js", ".jsx", ".ts", ".tsx"],
  },
  module: {
    rules: [
      {
        test: /\.tsx?$/,
        loader: "awesome-typescript-loader",
      },
    ],
  }
};