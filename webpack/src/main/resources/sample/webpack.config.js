/*
    webpack的配置文件
    作用：指示webpack干哪些活（当你运行webpack指令时，会加载里面的配置）

    所有构建工具都是基于nodejs平台运行的，模块化默认采用commonjs
 */

// resolve用来拼接绝对路径的方法
const {resolve} = require('path')
const HtmlWebpackPlugin = require('html-webpack-plugin')
const MiniCssExtractPlugin = require('mini-css-extract-plugin')
const OptimizeCssAssetsWebpackPlugin = require('optimize-css-assets-webpack-plugin')
const webpack = require('webpack')
const AddAssetHtmlWebpackPlugin = require('add-asset-html-webpack-plugin')

// 设置nodejs环境变量：决定使用browserslist的哪个环境
process.env.NODE_ENV = "production"

// 复用loader
const commonCssLoader = [
    // use数组中loader执行顺序：从右到左，从下到上 依次执行
    // 创建style标签，将js中的css样式资源插入进去，添加到head中生效
    // 'style-loader',
    // 这个loader取代style-loader，作用：提取js中的css成单独文件
    MiniCssExtractPlugin.loader,
    // 将css文件变成commonjs模块加载到js中，里面内容是样式字符串
    'css-loader',
    /*
        css兼容性处理：postcss --> postcss-loader postcss-preset-env

        帮postcss找到package.json中browserslist里面的配置，通过配置加载指定的css兼容性样式

        "browserslist": {
            // 开发环境 --> 设置node环境变量：process.env.NODE_ENV = development
            "development": [
              "last 1 chrome version",
              "last 1 firefox version",
              "last 1 safari version"
            ],
            "production": [
              ">0.2%",
              "not dead",
              "not op_mini all"
            ]
         }
     */
    // 使用loader的默认配置
    // 'postcss-loader'
    // 修改loader的配置
    {
        loader: 'postcss-loader',
        options: {
            postcssOptions: {
                plugins: [
                    [
                        'postcss-preset-env',
                        {
                            // Options
                        },
                    ],
                ],
            },
        },
    }
]

/*
    tree shaking：去除无用代码
        前提：1. 必须使用ES6模块化    2. 开启production环境
        作用：减少代码体积

        在package.json中配置
            "sideEffects": false 所有代码都没有副作用（都可以进行tree shaking）
                问题：可能会把css / @babel/polyfill（副作用）文件干掉
            "sideEffects": ["*.css", "*.less"]

 */

/*
    缓存：
        babel缓存
            cacheDirectory: true
            --> 让第二次打包构建速度更快
        文件资源缓存
            hash：每次webpack构建时会生成一个唯一的hash值。
                问题：因为js和css同时使用一个hash值
                    如果重新打包，会导致所有缓存失效。（可能我却只改动一个文件）
            chunkhash：根据chunk生成的hash值。如果打包来源于同一个chunk，那么hash值就一样
                问题：js和css的hash值还是一样的
                    因为css是在js中被引入的，所以同属于一个chunk
            contenthash：根据文件的内容生成hash值。不同文件hash值一定不一样
            --> 让代码上线运行缓存更好使用
 */

module.exports = {
    // webpack配置

    // 入口起点
    // entry: './src/dummy.js',
    entry: {
        // 多入口：有一个入口，最终输出就一个bundle
        dummy: './src/dummy.js',
        // index: './src/index.js',
    },
    // 输出
    output: {
        // 输出文件名, [name]：取文件名
        filename: 'js/[name].[contenthash:10].js',
        // 输出路径
        // __dirname是nodejs的变量，代表当前文件目录的绝对路径
        path: resolve(__dirname, 'build'),
        // 所有资源引入公共路径前缀： 'imgs/a.jpg' --> '/imgs/a.jpg' 相当于contextPath
        publicPath: '/skillone/webpack/sample/build/'
    },
    // loader的配置
    // 不同文件必须配置不同loader处理
    module: {
        rules: [
            //详细loader的配置

            /*
                正常来讲，一个文件只能被一个loader处理
                当一个文件要被多个loader处理，那么一定要指定loader执行的先后顺序：
                    先执行eslint，再执行babel
             */
            /*
                语法检查：eslint-loader eslint
                注意：只检查自己写的源代码，第三方的库是不用检查的
                设置检查规则：
                package.json中eslintConfig中设置
                "eslintConfig": {
                    "extends": "airbnb-base"
                }
                airbnb -> eslint-config-airbnb-base eslint-plugin-import eslint
                如果有些代码强制不需要检查，比如
                // eslint-disable-next-line
                console.log(xxx)
             */
            {
                test: /\.js$/,
                exclude: /node_modules/,
                // 优先执行
                enforce: 'pre',
                loader: 'eslint-loader',
                options: {
                    // 自动修复
                    fix: true
                }
            },
            {
                // 以下loader只会匹配一个
                // 注意：不能有两个配置处理同一种类型文件
                oneOf: [
                    {
                        // 配置哪些文件
                        test: /\.css$/,
                        // 使用哪些loader进行处理
                        use: [...commonCssLoader]
                    },
                    {
                        test: /\.less$/,
                        // 要使用多个loader处理，用use
                        use: [
                            ...commonCssLoader,
                            // 将less文件编译成css文件
                            // 需要下载less-loader和less
                            'less-loader'
                        ]
                    },
                    {
                        test: /\.html$/,
                        // 处理html文件的img图片（负责引入img，从而能被url-loader进行处理）
                        loader: 'html-loader'
                    },
                    /*
                        js兼容性处理：babel-loader @babel/preset-env @babel/core
                        1. 基本js兼容性处理 --> @babel/preset-env
                        问题：只能转换基本语法，如promise不能转换
                        2. 全部js兼容性处理 --> @babel/polyfill （太大，不推荐）
                        3. 需要做兼容性处理的才做：按需加载 --> core-js
                     */
                    {
                        test: /\.(js|jsx)$/,
                        exclude: /node_modules/,
                        use: [
                            /*
                                开启多进程打包.
                                进程启动大概为600ms，进程通信也有开销。
                                只有工作消耗时间比较长，才需要多进程打包
                             */
                            // 'thread-loader',
                            {
                                loader: 'thread-loader',
                                options: {
                                    workers: 8 // 进程8个
                                }
                            },
                            {
                                loader: 'babel-loader',
                                options: {
                                    // 预设：指示babel做怎么样的兼容性处理
                                    presets: [
                                        [
                                            "@babel/preset-env", // 基本语法的js处理，如箭头函数
                                            {
                                                useBuiltIns: "usage", // 按需加载处理
                                                corejs: {
                                                    "version": 3
                                                },
                                                // 指定兼容性做到哪个版本浏览器
                                                targets: {
                                                    "chrome": "60",
                                                    "firefox": "50",
                                                    "ie": "9",
                                                    "safari": "10",
                                                    "edge": "17"
                                                }
                                            }
                                        ]
                                    ],
                                    // 开启babel缓存
                                    // 第二次构建时，会读取之前的缓存
                                    cacheDirectory: true
                                }
                            }
                        ]
                    },
                    {
                        // 问题：默认处理不了html中img图片
                        // 处理图片资源
                        test: /\.(jpg|png|gif)$/,
                        // 使用一个loader
                        loader: 'url-loader',
                        options: {
                            // 图片大小小于8kb，就会被base64处理
                            // 优点：减少请求数量（减轻服务器压力）
                            // 缺点：图片体积会增大（文件请求速度更慢）
                            limit: 8 * 1024,
                            // esModule: false,
                            // 给图片进行重命名
                            // [hash:10]取图片的hash的前10位
                            // [ext]取文件原来扩展名
                            name: '[hash:10].[ext]',
                            outputPath: 'imgs'
                        }
                    },
                    // 打包其他资源（除了html/js/css资源以为的资源）
                    {
                        // 排除css/js/html资源
                        exclude: /\.(css|js|html|less|png|jpg|gif|json)$/,
                        loader: 'file-loader',
                        options: {
                            name: '[hash:10].[ext]',
                            outputPath: 'media'
                        }
                    }
                ]
            }
        ]
    },
    // plugin的配置
    plugins: [
        // 详细plugins的配置

        // html-webpack-plugin
        // 功能：默认会创建一个空的html，自动引入打包输出的所有资源（js/css）
        // 需求：需要有结构的html文件
        new HtmlWebpackPlugin({
            // 复制 一个html文件，并自动引入打包输出的所有资源（js/css）
            template: './src/test.html',
            // 压缩html代码
            minify: {
                // 移除空格
                collapseWhitespace: true,
                // 移除注释
                removeComments: true
            }
        }),
        new MiniCssExtractPlugin({
            // 对输出的css文件进行重命名
            filename: 'css/main.[contenthash:10].css'
        }),
        // 压缩css
        new OptimizeCssAssetsWebpackPlugin(),
        // 告诉webpack哪些库不参与打包，同时使用时的名称也得改变
        new webpack.DllReferencePlugin({
            manifest: resolve(__dirname, 'dll/manifest.json')
        }),
        // 将某个文件打包输出去，并在html中自动引入该文件资源
        new AddAssetHtmlWebpackPlugin([
            { filepath: resolve(__dirname, 'dll/jquery.js') },
            { filepath: resolve(__dirname, 'dll/react.js') }
        ])
    ],
    // 模式 development or production
    // production会压缩js文件
    mode: 'production',
    // 开发服务器devServer：用来自动化（自动编译，自动打开浏览器，自动刷新浏览器）
    // 特点：只会在内存中编译打包，不会有任何输出
    // 启动devServer指令为：npx webpack-dev-server
    /*devServer: {
        contentBase: resolve(__dirname, 'build'),
        // 启动gzip压缩
        compress: true,
        // 端口号
        port: 3000,
        // 自动打开浏览器
        open: true
    }*/
    /*
        source-map：一种提供源代码到构建后代码映射技术（如果构建后代码出错了，通过映射可以追踪到源代码错误）
            错误代码准确信息 和 源代码的错误位置
        inline-source-map：内联（只生成一个内联source-map）
            错误代码准确信息 和 源代码的错误位置
        hidden-source-map：外部
            不能追踪源代码的错误位置，只能提示到构建后代码到错误位置
        eval-source-map：内联（每一个文件都生成对应的source-map）
            错误代码准确信息 和 源代码的错误位置
        nosources-source-map：外部
            错误代码准确信息 但没有任何源代码信息
        cheap-source-map：外部
            错误代码准确信息 和 源代码的错误位置 但只能精确到行
        cheap-module-source-map：外部
            错误代码准确信息 和 源代码的错误位置 但只能精确到行 module会将loader的source map加入

        速度      eval-cheap-source-map -> eval-source-map
        调试友好   source-map -> cheap-module-source-map -> cheap-source-map

        开发推荐        eval-source-map / eval-cheap-module-source-map
        生产推荐        nosources-source-map [全部隐藏] / hidden-source-map [只隐藏源代码]
                      source-map / cheap-module-source-map
     */
    devtool: 'cheap-module-source-map',
    /*
        1. 可以将node_modules中代码单独打包一个chunk最终输出
        2. 自动分析多入口chunk中，有没有公共的文件。如果有会打包成单独的一个chunk
     */
    optimization: {
        splitChunks: {
            chunks: 'all'
        }
    },
    /*externals: {
        // 绝对jQuery被打包进来
        jquery: 'jQuery',
        react: 'React',
        'react-dom': 'ReactDOM',
        redux: 'Redux',
        'react-redux': 'React-redux',
        'prop-types': 'Prop-types',
        babel: 'Babel',
        axios: 'Axios'
    }*/
}