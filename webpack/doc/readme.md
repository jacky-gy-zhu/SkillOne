# Webpack
> 对静态资源打包的工具

## 五个核心概念
    1. Entry    入口指示webpack以哪个文件为入口起点开始打包，分析构建内部依赖图。
    2. Output   输出指示webpack打包后资源bundles输出到哪里，以及如何命名。
    3. Loader   让webpack能够去处理那些非Javascript文件。
    4. Plugins  插件可以用于执行更多复杂的任务，从打包优化和压缩，到重新定义环境中的变量等。
    5. Mode     开发模式以及生成模式。

## 安装
    npm install webpack webpack-cli -g
    npm install webpack webpack-cli -D
    npm install css-loader style-loader less less-loader file-loader url-loader html-loader html-webpack-plugin -D
    npm install webpack-dev-server -D
    npm install mini-css-extract-plugin -D // 将css文件从js文件中单独提取出来
    npm install postcss postcss-loader postcss-preset-env -D // css兼容性处理
    npm install optimize-css-assets-webpack-plugin -D // 压缩css

## 运行指令
    开发环境：webpack ./src/index.js -o ./build/built.js --mode=development
    webpack会以./src/index.js为入口文件开始打包，打包后输出到./build/built.js
    整体打包环境，是开发环境
    
    生产环境：webpack ./src/index.js -o ./build/built.js --mode=production
    webpack会以./src/index.js为入口文件开始打包，打包后输出到./build/built.js
    整体打包环境，是生产环境
    
    webpack可以处理js和json资源，不能处理css/img等其他资源。
    生产和开发环境将ES6的模块化编译成浏览器识别的模块化。
    生产环境比开发环境多一个压缩js代码。