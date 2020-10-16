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
    npm install eslint-config-airbnb-base eslint-plugin-import eslint-loader eslint -D // js语法检查，自动修正
    npm install babel-loader @babel/preset-env @babel/core @babel/polyfill -D // js兼容性处理（ES6语法IE不支持）
    npm install thread-loader -D // 多进程打包
    npm install add-asset-html-webpack-plugin -D // Dll,将某个文件打包输出去，并在html中自动引入该文件资源

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

## 性能优化
> 开发环境性能优化
> 生产环境性能优化

### 开发环境性能优化
> 优化打包构建速度
> 优化代码调试功能

### 生产环境性能优化
> 优化打包构建速度
> 优化代码运行的性能

### 懒加载以及代码分割
    import（/* webpackChunkName: 'test', webpackPrefetch: true */'./test').then(mul => {
        ....
    });

### DLL 动态连接库
    [webpack.config.js]
    new webpack.DllReferencePlugin({
        manifest: resolve(__dirname, 'dll/manifest.json')
    }),
    // 将某个文件打包输出去，并在html中自动引入该文件资源
    new AddAssetHtmlWebpackPlugin([
        { filepath: resolve(__dirname, 'dll/jquery.js') },
        { filepath: resolve(__dirname, 'dll/react.js') }
    ])
    
    [webpack.dll.js]
    const {resolve} = require('path')
    const webpack = require('webpack')
    module.exports = {
        entry: {
            // 最终打包生成的[name] --> jquery
            // ['jquery'] --> 要打包的库是jquery
            jquery: ['jquery'],
            // 将多个第三方包统一打包成一个包
            react: ['react', 'react-dom', 'react-router-dom']
        },
        output: {
            filename: '[name].js',
            path: resolve(__dirname, 'dll'),
            library: '[name]_[hash]', // 打包的库里面向外暴露出去的内容叫什么名字
        },
        plugins: [
            // 打包生成一个manifest.json --> 提供和jquery映射
            new webpack.DllPlugin({
                name: '[name]_[hash]', // 映射库的暴露的内容名称
                path: resolve(__dirname, 'dll/manifest.json') // 输出文件路径
            })
        ],
        mode: 'production'
    }

## React 打包
    npm install --save-dev webpack webpack-cli webpack-dev-server
    npm install react react-dom react-router-dom redux react-redux redux-thunk axios prop-types
    npm install --save-dev @babel/core babel-loader @babel/preset-env @babel/preset-react
    npm install --save-dev css-loader style-loader postcss-loader postcss less less-loader
    npm install --save-dev file-loader url-loader
    npm install --save-dev thread-loader
    npm install --save-dev html-loader
    npm install --save-dev autoprefixer
    npm install --save-dev html-webpack-plugin
    npm install --save-dev mini-css-extract-plugin
    npm install --save-dev optimize-css-assets-webpack-plugin
    npm install --save-dev add-asset-html-webpack-plugin
    npm install --save-dev eslint-config-airbnb-base eslint-plugin-import eslint-loader eslint
    npm install --save-dev postcss postcss-loader postcss-preset-env
    npm install --save-dev react-bootstrap bootstrap